package com.trustvip.web.rest;

import com.trustvip.VipAdpqApp;

import com.trustvip.domain.TaskOwner;
import com.trustvip.repository.TaskOwnerRepository;
import com.trustvip.service.TaskOwnerService;
import com.trustvip.repository.search.TaskOwnerSearchRepository;
import com.trustvip.service.dto.TaskOwnerDTO;
import com.trustvip.service.mapper.TaskOwnerMapper;
import com.trustvip.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.trustvip.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TaskOwnerResource REST controller.
 *
 * @see TaskOwnerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VipAdpqApp.class)
public class TaskOwnerResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private TaskOwnerRepository taskOwnerRepository;

    @Autowired
    private TaskOwnerMapper taskOwnerMapper;

    @Autowired
    private TaskOwnerService taskOwnerService;

    @Autowired
    private TaskOwnerSearchRepository taskOwnerSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTaskOwnerMockMvc;

    private TaskOwner taskOwner;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaskOwnerResource taskOwnerResource = new TaskOwnerResource(taskOwnerService);
        this.restTaskOwnerMockMvc = MockMvcBuilders.standaloneSetup(taskOwnerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TaskOwner createEntity(EntityManager em) {
        TaskOwner taskOwner = new TaskOwner()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL);
        return taskOwner;
    }

    @Before
    public void initTest() {
        taskOwnerSearchRepository.deleteAll();
        taskOwner = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaskOwner() throws Exception {
        int databaseSizeBeforeCreate = taskOwnerRepository.findAll().size();

        // Create the TaskOwner
        TaskOwnerDTO taskOwnerDTO = taskOwnerMapper.toDto(taskOwner);
        restTaskOwnerMockMvc.perform(post("/api/task-owners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskOwnerDTO)))
            .andExpect(status().isCreated());

        // Validate the TaskOwner in the database
        List<TaskOwner> taskOwnerList = taskOwnerRepository.findAll();
        assertThat(taskOwnerList).hasSize(databaseSizeBeforeCreate + 1);
        TaskOwner testTaskOwner = taskOwnerList.get(taskOwnerList.size() - 1);
        assertThat(testTaskOwner.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTaskOwner.getEmail()).isEqualTo(DEFAULT_EMAIL);

        // Validate the TaskOwner in Elasticsearch
        TaskOwner taskOwnerEs = taskOwnerSearchRepository.findOne(testTaskOwner.getId());
        assertThat(taskOwnerEs).isEqualToIgnoringGivenFields(testTaskOwner);
    }

    @Test
    @Transactional
    public void createTaskOwnerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taskOwnerRepository.findAll().size();

        // Create the TaskOwner with an existing ID
        taskOwner.setId(1L);
        TaskOwnerDTO taskOwnerDTO = taskOwnerMapper.toDto(taskOwner);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaskOwnerMockMvc.perform(post("/api/task-owners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskOwnerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TaskOwner in the database
        List<TaskOwner> taskOwnerList = taskOwnerRepository.findAll();
        assertThat(taskOwnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTaskOwners() throws Exception {
        // Initialize the database
        taskOwnerRepository.saveAndFlush(taskOwner);

        // Get all the taskOwnerList
        restTaskOwnerMockMvc.perform(get("/api/task-owners?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskOwner.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }

    @Test
    @Transactional
    public void getTaskOwner() throws Exception {
        // Initialize the database
        taskOwnerRepository.saveAndFlush(taskOwner);

        // Get the taskOwner
        restTaskOwnerMockMvc.perform(get("/api/task-owners/{id}", taskOwner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(taskOwner.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTaskOwner() throws Exception {
        // Get the taskOwner
        restTaskOwnerMockMvc.perform(get("/api/task-owners/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaskOwner() throws Exception {
        // Initialize the database
        taskOwnerRepository.saveAndFlush(taskOwner);
        taskOwnerSearchRepository.save(taskOwner);
        int databaseSizeBeforeUpdate = taskOwnerRepository.findAll().size();

        // Update the taskOwner
        TaskOwner updatedTaskOwner = taskOwnerRepository.findOne(taskOwner.getId());
        // Disconnect from session so that the updates on updatedTaskOwner are not directly saved in db
        em.detach(updatedTaskOwner);
        updatedTaskOwner
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL);
        TaskOwnerDTO taskOwnerDTO = taskOwnerMapper.toDto(updatedTaskOwner);

        restTaskOwnerMockMvc.perform(put("/api/task-owners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskOwnerDTO)))
            .andExpect(status().isOk());

        // Validate the TaskOwner in the database
        List<TaskOwner> taskOwnerList = taskOwnerRepository.findAll();
        assertThat(taskOwnerList).hasSize(databaseSizeBeforeUpdate);
        TaskOwner testTaskOwner = taskOwnerList.get(taskOwnerList.size() - 1);
        assertThat(testTaskOwner.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTaskOwner.getEmail()).isEqualTo(UPDATED_EMAIL);

        // Validate the TaskOwner in Elasticsearch
        TaskOwner taskOwnerEs = taskOwnerSearchRepository.findOne(testTaskOwner.getId());
        assertThat(taskOwnerEs).isEqualToIgnoringGivenFields(testTaskOwner);
    }

    @Test
    @Transactional
    public void updateNonExistingTaskOwner() throws Exception {
        int databaseSizeBeforeUpdate = taskOwnerRepository.findAll().size();

        // Create the TaskOwner
        TaskOwnerDTO taskOwnerDTO = taskOwnerMapper.toDto(taskOwner);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTaskOwnerMockMvc.perform(put("/api/task-owners")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taskOwnerDTO)))
            .andExpect(status().isCreated());

        // Validate the TaskOwner in the database
        List<TaskOwner> taskOwnerList = taskOwnerRepository.findAll();
        assertThat(taskOwnerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTaskOwner() throws Exception {
        // Initialize the database
        taskOwnerRepository.saveAndFlush(taskOwner);
        taskOwnerSearchRepository.save(taskOwner);
        int databaseSizeBeforeDelete = taskOwnerRepository.findAll().size();

        // Get the taskOwner
        restTaskOwnerMockMvc.perform(delete("/api/task-owners/{id}", taskOwner.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean taskOwnerExistsInEs = taskOwnerSearchRepository.exists(taskOwner.getId());
        assertThat(taskOwnerExistsInEs).isFalse();

        // Validate the database is empty
        List<TaskOwner> taskOwnerList = taskOwnerRepository.findAll();
        assertThat(taskOwnerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchTaskOwner() throws Exception {
        // Initialize the database
        taskOwnerRepository.saveAndFlush(taskOwner);
        taskOwnerSearchRepository.save(taskOwner);

        // Search the taskOwner
        restTaskOwnerMockMvc.perform(get("/api/_search/task-owners?query=id:" + taskOwner.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taskOwner.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskOwner.class);
        TaskOwner taskOwner1 = new TaskOwner();
        taskOwner1.setId(1L);
        TaskOwner taskOwner2 = new TaskOwner();
        taskOwner2.setId(taskOwner1.getId());
        assertThat(taskOwner1).isEqualTo(taskOwner2);
        taskOwner2.setId(2L);
        assertThat(taskOwner1).isNotEqualTo(taskOwner2);
        taskOwner1.setId(null);
        assertThat(taskOwner1).isNotEqualTo(taskOwner2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaskOwnerDTO.class);
        TaskOwnerDTO taskOwnerDTO1 = new TaskOwnerDTO();
        taskOwnerDTO1.setId(1L);
        TaskOwnerDTO taskOwnerDTO2 = new TaskOwnerDTO();
        assertThat(taskOwnerDTO1).isNotEqualTo(taskOwnerDTO2);
        taskOwnerDTO2.setId(taskOwnerDTO1.getId());
        assertThat(taskOwnerDTO1).isEqualTo(taskOwnerDTO2);
        taskOwnerDTO2.setId(2L);
        assertThat(taskOwnerDTO1).isNotEqualTo(taskOwnerDTO2);
        taskOwnerDTO1.setId(null);
        assertThat(taskOwnerDTO1).isNotEqualTo(taskOwnerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(taskOwnerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taskOwnerMapper.fromId(null)).isNull();
    }
}
