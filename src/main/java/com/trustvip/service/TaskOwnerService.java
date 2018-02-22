package com.trustvip.service;

import com.trustvip.service.dto.TaskOwnerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TaskOwner.
 */
public interface TaskOwnerService {

    /**
     * Save a taskOwner.
     *
     * @param taskOwnerDTO the entity to save
     * @return the persisted entity
     */
    TaskOwnerDTO save(TaskOwnerDTO taskOwnerDTO);

    /**
     * Get all the taskOwners.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TaskOwnerDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taskOwner.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TaskOwnerDTO findOne(Long id);

    /**
     * Delete the "id" taskOwner.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the taskOwner corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TaskOwnerDTO> search(String query, Pageable pageable);
}
