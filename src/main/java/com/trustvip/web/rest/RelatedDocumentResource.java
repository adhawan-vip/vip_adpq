package com.trustvip.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.trustvip.service.RelatedDocumentService;
import com.trustvip.web.rest.errors.BadRequestAlertException;
import com.trustvip.web.rest.util.HeaderUtil;
import com.trustvip.web.rest.util.PaginationUtil;
import com.trustvip.service.dto.RelatedDocumentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing RelatedDocument.
 */
@RestController
@RequestMapping("/api")
public class RelatedDocumentResource {

    private final Logger log = LoggerFactory.getLogger(RelatedDocumentResource.class);

    private static final String ENTITY_NAME = "relatedDocument";

    private final RelatedDocumentService relatedDocumentService;

    public RelatedDocumentResource(RelatedDocumentService relatedDocumentService) {
        this.relatedDocumentService = relatedDocumentService;
    }

    /**
     * POST  /related-documents : Create a new relatedDocument.
     *
     * @param relatedDocumentDTO the relatedDocumentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new relatedDocumentDTO, or with status 400 (Bad Request) if the relatedDocument has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/related-documents")
    @Timed
    public ResponseEntity<RelatedDocumentDTO> createRelatedDocument(@Valid @RequestBody RelatedDocumentDTO relatedDocumentDTO) throws URISyntaxException {
        log.debug("REST request to save RelatedDocument : {}", relatedDocumentDTO);
        if (relatedDocumentDTO.getId() != null) {
            throw new BadRequestAlertException("A new relatedDocument cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelatedDocumentDTO result = relatedDocumentService.save(relatedDocumentDTO);
        return ResponseEntity.created(new URI("/api/related-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /related-documents : Updates an existing relatedDocument.
     *
     * @param relatedDocumentDTO the relatedDocumentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated relatedDocumentDTO,
     * or with status 400 (Bad Request) if the relatedDocumentDTO is not valid,
     * or with status 500 (Internal Server Error) if the relatedDocumentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/related-documents")
    @Timed
    public ResponseEntity<RelatedDocumentDTO> updateRelatedDocument(@Valid @RequestBody RelatedDocumentDTO relatedDocumentDTO) throws URISyntaxException {
        log.debug("REST request to update RelatedDocument : {}", relatedDocumentDTO);
        if (relatedDocumentDTO.getId() == null) {
            return createRelatedDocument(relatedDocumentDTO);
        }
        RelatedDocumentDTO result = relatedDocumentService.save(relatedDocumentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, relatedDocumentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /related-documents : get all the relatedDocuments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of relatedDocuments in body
     */
    @GetMapping("/related-documents")
    @Timed
    public ResponseEntity<List<RelatedDocumentDTO>> getAllRelatedDocuments(Pageable pageable) {
        log.debug("REST request to get a page of RelatedDocuments");
        Page<RelatedDocumentDTO> page = relatedDocumentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/related-documents");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /related-documents/:id : get the "id" relatedDocument.
     *
     * @param id the id of the relatedDocumentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the relatedDocumentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/related-documents/{id}")
    @Timed
    public ResponseEntity<RelatedDocumentDTO> getRelatedDocument(@PathVariable Long id) {
        log.debug("REST request to get RelatedDocument : {}", id);
        RelatedDocumentDTO relatedDocumentDTO = relatedDocumentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(relatedDocumentDTO));
    }

    /**
     * DELETE  /related-documents/:id : delete the "id" relatedDocument.
     *
     * @param id the id of the relatedDocumentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/related-documents/{id}")
    @Timed
    public ResponseEntity<Void> deleteRelatedDocument(@PathVariable Long id) {
        log.debug("REST request to delete RelatedDocument : {}", id);
        relatedDocumentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/related-documents?query=:query : search for the relatedDocument corresponding
     * to the query.
     *
     * @param query the query of the relatedDocument search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/related-documents")
    @Timed
    public ResponseEntity<List<RelatedDocumentDTO>> searchRelatedDocuments(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of RelatedDocuments for query {}", query);
        Page<RelatedDocumentDTO> page = relatedDocumentService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/related-documents");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
