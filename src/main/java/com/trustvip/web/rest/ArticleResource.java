package com.trustvip.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.trustvip.domain.enumeration.ArticleStatus;
import com.trustvip.domain.enumeration.ArticleType;
import com.trustvip.domain.enumeration.TaskStatus;
import com.trustvip.security.AuthoritiesConstants;
import com.trustvip.service.ArticleService;
import com.trustvip.service.RelatedDocumentService;
import com.trustvip.service.TaskService;
import com.trustvip.service.UserService;
import com.trustvip.service.dto.ArticleDTO;
import com.trustvip.service.dto.TaskDTO;
import com.trustvip.web.rest.errors.BadRequestAlertException;
import com.trustvip.web.rest.util.HeaderUtil;
import com.trustvip.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Article.
 */
@RestController
@RequestMapping("/api")
public class ArticleResource {

    private final Logger log = LoggerFactory.getLogger(ArticleResource.class);

    private static final String ENTITY_NAME = "article";

    private final ArticleService articleService;
    private final RelatedDocumentService documentService;
    private final TaskService taskService;
    private final UserService userService;

    public ArticleResource(ArticleService articleService, RelatedDocumentService documentService,
            TaskService taskService, UserService userService) {
        this.articleService = articleService;
        this.documentService = documentService;
        this.taskService = taskService;
        this.userService = userService;
    }

    /**
     * POST /articles : Create a new article.
     *
     * @param articleDTO
     *            the articleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new
     *         articleDTO, or with status 400 (Bad Request) if the article has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PostMapping("/articles")
    @Timed
    public ResponseEntity<ArticleDTO> createArticle(@Valid @RequestBody ArticleDTO articleDTO)
            throws URISyntaxException {
        log.debug("REST request to save Article : {}", articleDTO);
        if (articleDTO.getId() != null) {
            throw new BadRequestAlertException("A new article cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArticleDTO article = articleService.save(articleDTO);
        if(articleDTO.getStatus().toString()=="DRAFT") {
        	createTask(article);
        }
        return ResponseEntity.created(new URI("/api/articles/" + article.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, article.getId().toString())).body(article);
    }
    
    
   
    
  

    /**
     * PUT /articles : Updates an existing article.
     *
     * @param articleDTO
     *            the articleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated
     *         articleDTO, or with status 400 (Bad Request) if the articleDTO is not
     *         valid, or with status 500 (Internal Server Error) if the articleDTO
     *         couldn't be updated
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @PutMapping("/articles")
    @Timed
    public ResponseEntity<ArticleDTO> updateArticle(@Valid @RequestBody ArticleDTO articleDTO)
            throws URISyntaxException {
        log.debug("REST request to update Article : {}", articleDTO);
        if (articleDTO.getId() == null) {
            return createArticle(articleDTO);
        }
        ArticleDTO result = articleService.save(articleDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, articleDTO.getId().toString())).body(result);
    }

    /**
     * GET /articles : get all the articles.
     *
     * @param pageable
     *            the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of articles in
     *         body
     */
    @GetMapping("/articles")
    @Timed
    public ResponseEntity<List<ArticleDTO>> getAllArticles(Pageable pageable) {
        log.debug("REST request to get a page of Articles");
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        // by default, only published articles are accessible
        Page<ArticleDTO> page = articleService.findAllByStatus(ArticleStatus.PUBLISHED, pageable);
        for (SimpleGrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
            // admins can see everything
            if (authority.getAuthority().equals(AuthoritiesConstants.ADMIN)) {
                page = articleService.findAll(pageable);
                break;
            }
            // reviewers and authors can see drafts
            else if (authority.getAuthority().equals(AuthoritiesConstants.REVIEWER)
                    || authority.getAuthority().equals(AuthoritiesConstants.AUTHOR)) {
                page = articleService.findAllByStatus(ArticleStatus.DRAFT, pageable);
                ;
                break;
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/articles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET /articles/:id : get the "id" article.
     *
     * @param id
     *            the id of the articleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the articleDTO,
     *         or with status 404 (Not Found)
     */
    @GetMapping("/articles/{id}")
    @Timed
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {
        log.debug("REST request to get Article : {}", id);
        ArticleDTO articleDTO = articleService.findOne(id);

        articleDTO.setDocList(documentService.findAllByArticleId(articleDTO.getId()));
        log.debug("DOCLIST: " + articleDTO.getDocList().toString());

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(articleDTO));
    }

    /**
     * DELETE /articles/:id : delete the "id" article.
     *
     * @param id
     *            the id of the articleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/articles/{id}")
    @Timed
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        log.debug("REST request to delete Article : {}", id);
        articleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH /_search/articles?query=:query : search for the article corresponding
     * to the query.
     *
     * @param query
     *            the query of the article search
     * @param pageable
     *            the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/articles")
    @Timed
    public ResponseEntity<List<ArticleDTO>> searchArticles(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Articles for query {}", query);
        Page<ArticleDTO> page = articleService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/articles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * POST /articles : Create a new article.
     *
     * @param articleDTO
     *            the articleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new
     *         articleDTO, or with status 400 (Bad Request) if the article has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @GetMapping("/share/{email}/{id}")
    @Timed
    public ResponseEntity<Void> shareArticle(@PathVariable String email, @PathVariable Long id)
            throws URISyntaxException {
        articleService.sendEmail(email, id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert(ENTITY_NAME, email)).build();
    }
    
    

    /**
     * POST /articles : Create a new article.
     *
     * @param articleDTO
     *            the articleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new
     *         articleDTO, or with status 400 (Bad Request) if the article has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @GetMapping("/labels/pie")
    @Timed
    public ResponseEntity<List<String>> getPieChartLabels()
            throws URISyntaxException {
        List<String> labels = new ArrayList<String>();
        labels.add(ArticleType.CONTENT.toString());
        labels.add(ArticleType.JOBAID.toString());
        labels.add(ArticleType.PACKAGE.toString());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(labels));
    }
    
    /**
     * POST /articles : Create a new article.
     *
     * @param articleDTO
     *            the articleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new
     *         articleDTO, or with status 400 (Bad Request) if the article has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @GetMapping("/data/pie")
    @Timed
    public ResponseEntity<List<Long>> getPieChartTypeData()
            throws URISyntaxException {
        List<Long> data = new ArrayList<Long>();
        data.add(articleService.getCountByType(ArticleType.CONTENT));
        data.add(articleService.getCountByType(ArticleType.JOBAID));
        data.add(articleService.getCountByType(ArticleType.PACKAGE));
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(data));
    }
    
    /**
     * POST /articles : Create a new article.
     *
     * @param articleDTO
     *            the articleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new
     *         articleDTO, or with status 400 (Bad Request) if the article has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @GetMapping("/labels/pie/status")
    @Timed
    public ResponseEntity<List<String>> getPieChartLabelsByStatus()
            throws URISyntaxException {
        List<String> labels = new ArrayList<String>();
        labels.add(ArticleStatus.DRAFT.toString());
        labels.add(ArticleStatus.PUBLISHED.toString());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(labels));
    }
    
    /**
     * POST /articles : Create a new article.
     *
     * @param articleDTO
     *            the articleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new
     *         articleDTO, or with status 400 (Bad Request) if the article has
     *         already an ID
     * @throws URISyntaxException
     *             if the Location URI syntax is incorrect
     */
    @GetMapping("/data/pie/status")
    @Timed
    public ResponseEntity<List<Long>> getPieChartTypeDataByStatus()
            throws URISyntaxException {
        List<Long> data = new ArrayList<Long>();
        data.add(articleService.getCountByStatus(ArticleStatus.DRAFT));
        data.add(articleService.getCountByStatus(ArticleStatus.PUBLISHED));
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(data));
    }
    
    private void createTask(ArticleDTO article)
    {
        TaskDTO task = new TaskDTO();
        task.setArticleId(article.getId());
        task.setTaskName("Review: " + article.getArticleName());
        task.setDueDate(LocalDate.now().plusDays(7));
        task.setDescription(task.getTaskName());
        task.setStatus(TaskStatus.OPEN);
        taskService.save(task);
    }
}
