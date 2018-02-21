package com.trustvip.service;

import com.trustvip.service.dto.RelatedDocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing RelatedDocument.
 */
public interface RelatedDocumentService {

    /**
     * Save a relatedDocument.
     *
     * @param relatedDocumentDTO the entity to save
     * @return the persisted entity
     */
    RelatedDocumentDTO save(RelatedDocumentDTO relatedDocumentDTO);

    /**
     * Get all the relatedDocuments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RelatedDocumentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" relatedDocument.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RelatedDocumentDTO findOne(Long id);

    /**
     * Delete the "id" relatedDocument.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the relatedDocument corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RelatedDocumentDTO> search(String query, Pageable pageable);
}
