package com.trustvip.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trustvip.domain.Article;
import com.trustvip.domain.enumeration.ArticleStatus;
import com.trustvip.repository.ArticleRepository;
import com.trustvip.repository.search.ArticleSearchRepository;
import com.trustvip.security.SecurityUtils;
import com.trustvip.service.ArticleService;
import com.trustvip.service.dto.ArticleDTO;
import com.trustvip.service.mapper.ArticleMapper;

/**
 * Service Implementation for managing Article.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    private final ArticleSearchRepository articleSearchRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper,
            ArticleSearchRepository articleSearchRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.articleSearchRepository = articleSearchRepository;
    }

    /**
     * Save a article.
     *
     * @param articleDTO
     *            the entity to save
     * @return the persisted entity
     */
    @Override
    public ArticleDTO save(ArticleDTO articleDTO) {
        log.debug("Request to save Article : {}", articleDTO);
        articleDTO = populateTimeStamp(articleDTO);
        Article article = articleMapper.toEntity(articleDTO);
        article = articleRepository.save(article);
        ArticleDTO result = articleMapper.toDto(article);
        articleSearchRepository.save(article);
        return result;
    }

    /**
     * Populate timestamp information for audit and history
     * 
     * @param articleDTO
     *            - DTO to update
     * @return articleDTO - populated
     */
    ArticleDTO populateTimeStamp(ArticleDTO articleDTO) {
        if (articleDTO.getCreatedBy() == null)
            articleDTO.setCreatedBy(SecurityUtils.getCurrentUserLogin().get());
        if (articleDTO.getCreatedOn() == null)
            articleDTO.setCreatedOn(LocalDate.now());
        if (articleDTO.getStatus() == null)
            articleDTO.setStatus(ArticleStatus.DRAFT);
        articleDTO.setModifiedBy(SecurityUtils.getCurrentUserLogin().get());
        articleDTO.setModifiedOn(LocalDate.now());
        return articleDTO;
    }

    /**
     * Get all the articles.
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Articles");
        return articleRepository.findAll(pageable).map(articleMapper::toDto);
    }

    
    
    /**
     * Get one article by id.
     *
     * @param id
     *            the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ArticleDTO findOne(Long id) {
        log.debug("Request to get Article : {}", id);
        Article article = articleRepository.findOne(id);
        return articleMapper.toDto(article);
    }

    /**
     * Delete the article by id.
     *
     * @param id
     *            the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Article : {}", id);
        articleRepository.delete(id);
        articleSearchRepository.delete(id);
    }

    /**
     * Search for the article corresponding to the query.
     *
     * @param query
     *            the query of the search
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Articles for query {}", query);
        Page<Article> result = articleSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(articleMapper::toDto);
    }

    /**
     * Get all the articles by status
     *
     * @param pageable
     *            the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDTO> findAllByStatus(ArticleStatus status, Pageable pageable) {
        log.debug("Request to get all Articles");
        Article article = new Article();
        article.setStatus(status);
        Example<Article> example = Example.of(article);
        return articleRepository.findAll(example, pageable).map(articleMapper::toDto);
    }

}
