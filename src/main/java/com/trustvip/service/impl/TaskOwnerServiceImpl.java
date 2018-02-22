package com.trustvip.service.impl;

import com.trustvip.service.TaskOwnerService;
import com.trustvip.domain.TaskOwner;
import com.trustvip.repository.TaskOwnerRepository;
import com.trustvip.repository.search.TaskOwnerSearchRepository;
import com.trustvip.service.dto.TaskOwnerDTO;
import com.trustvip.service.mapper.TaskOwnerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing TaskOwner.
 */
@Service
@Transactional
public class TaskOwnerServiceImpl implements TaskOwnerService {

    private final Logger log = LoggerFactory.getLogger(TaskOwnerServiceImpl.class);

    private final TaskOwnerRepository taskOwnerRepository;

    private final TaskOwnerMapper taskOwnerMapper;

    private final TaskOwnerSearchRepository taskOwnerSearchRepository;

    public TaskOwnerServiceImpl(TaskOwnerRepository taskOwnerRepository, TaskOwnerMapper taskOwnerMapper, TaskOwnerSearchRepository taskOwnerSearchRepository) {
        this.taskOwnerRepository = taskOwnerRepository;
        this.taskOwnerMapper = taskOwnerMapper;
        this.taskOwnerSearchRepository = taskOwnerSearchRepository;
    }

    /**
     * Save a taskOwner.
     *
     * @param taskOwnerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaskOwnerDTO save(TaskOwnerDTO taskOwnerDTO) {
        log.debug("Request to save TaskOwner : {}", taskOwnerDTO);
        TaskOwner taskOwner = taskOwnerMapper.toEntity(taskOwnerDTO);
        taskOwner = taskOwnerRepository.save(taskOwner);
        TaskOwnerDTO result = taskOwnerMapper.toDto(taskOwner);
        taskOwnerSearchRepository.save(taskOwner);
        return result;
    }

    /**
     * Get all the taskOwners.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskOwnerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskOwners");
        return taskOwnerRepository.findAll(pageable)
            .map(taskOwnerMapper::toDto);
    }

    /**
     * Get one taskOwner by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TaskOwnerDTO findOne(Long id) {
        log.debug("Request to get TaskOwner : {}", id);
        TaskOwner taskOwner = taskOwnerRepository.findOne(id);
        return taskOwnerMapper.toDto(taskOwner);
    }

    /**
     * Delete the taskOwner by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskOwner : {}", id);
        taskOwnerRepository.delete(id);
        taskOwnerSearchRepository.delete(id);
    }

    /**
     * Search for the taskOwner corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskOwnerDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TaskOwners for query {}", query);
        Page<TaskOwner> result = taskOwnerSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(taskOwnerMapper::toDto);
    }
}
