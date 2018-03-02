package com.trustvip.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trustvip.domain.Task;
import com.trustvip.domain.enumeration.TaskStatus;
import com.trustvip.repository.TaskRepository;
import com.trustvip.repository.search.TaskSearchRepository;
import com.trustvip.service.TaskService;
import com.trustvip.service.dto.TaskDTO;
import com.trustvip.service.mapper.TaskMapper;

/**
 * Service Implementation for managing Task.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    private final TaskSearchRepository taskSearchRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, TaskSearchRepository taskSearchRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.taskSearchRepository = taskSearchRepository;
    }

    /**
     * Save a task.
     *
     * @param taskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        log.debug("Request to save Task : {}", taskDTO);
        Task task = taskMapper.toEntity(taskDTO);
        task = taskRepository.save(task);
        TaskDTO result = taskMapper.toDto(task);
        taskSearchRepository.save(task);
        return result;
    }

    /**
     * Get all the tasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tasks");
        return taskRepository.findAll(pageable)
            .map(taskMapper::toDto);
    }

    /**
     * Get one task by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TaskDTO findOne(Long id) {
        log.debug("Request to get Task : {}", id);
        Task task = taskRepository.findOne(id);
        return taskMapper.toDto(task);
    }

    /**
     * Delete the task by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.delete(id);
        taskSearchRepository.delete(id);
    }

    /**
     * Search for the task corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Tasks for query {}", query);
        Page<Task> result = taskSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(taskMapper::toDto);
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
    public Page<TaskDTO> findAllByStatus(TaskStatus status, Pageable pageable) {
        log.debug("Request to get tasks by status:" + status);
        Task task = new Task();
        task.setStatus(status);
        Example<Task> example = Example.of(task);
        return taskRepository.findAll(example, pageable).map(taskMapper::toDto);
    }
}
