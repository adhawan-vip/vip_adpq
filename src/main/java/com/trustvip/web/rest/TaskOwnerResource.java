package com.trustvip.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.trustvip.service.TaskOwnerService;
import com.trustvip.web.rest.errors.BadRequestAlertException;
import com.trustvip.web.rest.util.HeaderUtil;
import com.trustvip.web.rest.util.PaginationUtil;
import com.trustvip.service.dto.TaskOwnerDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing TaskOwner.
 */
@RestController
@RequestMapping("/api")
public class TaskOwnerResource {

    private final Logger log = LoggerFactory.getLogger(TaskOwnerResource.class);

    private static final String ENTITY_NAME = "taskOwner";

    private final TaskOwnerService taskOwnerService;

    public TaskOwnerResource(TaskOwnerService taskOwnerService) {
        this.taskOwnerService = taskOwnerService;
    }

    /**
     * POST  /task-owners : Create a new taskOwner.
     *
     * @param taskOwnerDTO the taskOwnerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new taskOwnerDTO, or with status 400 (Bad Request) if the taskOwner has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/task-owners")
    @Timed
    public ResponseEntity<TaskOwnerDTO> createTaskOwner(@RequestBody TaskOwnerDTO taskOwnerDTO) throws URISyntaxException {
        log.debug("REST request to save TaskOwner : {}", taskOwnerDTO);
        if (taskOwnerDTO.getId() != null) {
            throw new BadRequestAlertException("A new taskOwner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskOwnerDTO result = taskOwnerService.save(taskOwnerDTO);
        return ResponseEntity.created(new URI("/api/task-owners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /task-owners : Updates an existing taskOwner.
     *
     * @param taskOwnerDTO the taskOwnerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated taskOwnerDTO,
     * or with status 400 (Bad Request) if the taskOwnerDTO is not valid,
     * or with status 500 (Internal Server Error) if the taskOwnerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/task-owners")
    @Timed
    public ResponseEntity<TaskOwnerDTO> updateTaskOwner(@RequestBody TaskOwnerDTO taskOwnerDTO) throws URISyntaxException {
        log.debug("REST request to update TaskOwner : {}", taskOwnerDTO);
        if (taskOwnerDTO.getId() == null) {
            return createTaskOwner(taskOwnerDTO);
        }
        TaskOwnerDTO result = taskOwnerService.save(taskOwnerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taskOwnerDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /task-owners : get all the taskOwners.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of taskOwners in body
     */
    @GetMapping("/task-owners")
    @Timed
    public ResponseEntity<List<TaskOwnerDTO>> getAllTaskOwners(Pageable pageable) {
        log.debug("REST request to get a page of TaskOwners");
        Page<TaskOwnerDTO> page = taskOwnerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/task-owners");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /task-owners/:id : get the "id" taskOwner.
     *
     * @param id the id of the taskOwnerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the taskOwnerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/task-owners/{id}")
    @Timed
    public ResponseEntity<TaskOwnerDTO> getTaskOwner(@PathVariable Long id) {
        log.debug("REST request to get TaskOwner : {}", id);
        TaskOwnerDTO taskOwnerDTO = taskOwnerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(taskOwnerDTO));
    }

    /**
     * DELETE  /task-owners/:id : delete the "id" taskOwner.
     *
     * @param id the id of the taskOwnerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/task-owners/{id}")
    @Timed
    public ResponseEntity<Void> deleteTaskOwner(@PathVariable Long id) {
        log.debug("REST request to delete TaskOwner : {}", id);
        taskOwnerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/task-owners?query=:query : search for the taskOwner corresponding
     * to the query.
     *
     * @param query the query of the taskOwner search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/task-owners")
    @Timed
    public ResponseEntity<List<TaskOwnerDTO>> searchTaskOwners(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TaskOwners for query {}", query);
        Page<TaskOwnerDTO> page = taskOwnerService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/task-owners");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
