package com.trustvip.web.rest;

import com.trustvip.VipAdpqApp;

import com.trustvip.domain.RelatedDocument;
import com.trustvip.domain.Article;
import com.trustvip.repository.RelatedDocumentRepository;
import com.trustvip.service.RelatedDocumentService;
import com.trustvip.repository.search.RelatedDocumentSearchRepository;
import com.trustvip.service.dto.RelatedDocumentDTO;
import com.trustvip.service.mapper.RelatedDocumentMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.trustvip.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RelatedDocumentResource REST controller.
 *
 * @see RelatedDocumentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VipAdpqApp.class)
public class RelatedDocumentResourceIntTest {

    private static final String DEFAULT_DOC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOC_NAME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DOC_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DOC_FILE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_DOC_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DOC_FILE_CONTENT_TYPE = "image/png";

    @Autowired
    private RelatedDocumentRepository relatedDocumentRepository;

    @Autowired
    private RelatedDocumentMapper relatedDocumentMapper;

    @Autowired
    private RelatedDocumentService relatedDocumentService;

    @Autowired
    private RelatedDocumentSearchRepository relatedDocumentSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRelatedDocumentMockMvc;

    private RelatedDocument relatedDocument;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RelatedDocumentResource relatedDocumentResource = new RelatedDocumentResource(relatedDocumentService);
        this.restRelatedDocumentMockMvc = MockMvcBuilders.standaloneSetup(relatedDocumentResource)
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
    public static RelatedDocument createEntity(EntityManager em) {
        RelatedDocument relatedDocument = new RelatedDocument()
            .docName(DEFAULT_DOC_NAME)
            .docFile(DEFAULT_DOC_FILE)
            .docFileContentType(DEFAULT_DOC_FILE_CONTENT_TYPE);
        // Add required entity
        Article article = ArticleResourceIntTest.createEntity(em);
        em.persist(article);
        em.flush();
        relatedDocument.setArticle(article);
        return relatedDocument;
    }

    @Before
    public void initTest() {
        relatedDocumentSearchRepository.deleteAll();
        relatedDocument = createEntity(em);
    }

    @Test
    @Transactional
    public void createRelatedDocument() throws Exception {
        int databaseSizeBeforeCreate = relatedDocumentRepository.findAll().size();

        // Create the RelatedDocument
        RelatedDocumentDTO relatedDocumentDTO = relatedDocumentMapper.toDto(relatedDocument);
        restRelatedDocumentMockMvc.perform(post("/api/related-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatedDocumentDTO)))
            .andExpect(status().isCreated());

        // Validate the RelatedDocument in the database
        List<RelatedDocument> relatedDocumentList = relatedDocumentRepository.findAll();
        assertThat(relatedDocumentList).hasSize(databaseSizeBeforeCreate + 1);
        RelatedDocument testRelatedDocument = relatedDocumentList.get(relatedDocumentList.size() - 1);
        assertThat(testRelatedDocument.getDocName()).isEqualTo(DEFAULT_DOC_NAME);
        assertThat(testRelatedDocument.getDocFile()).isEqualTo(DEFAULT_DOC_FILE);
        assertThat(testRelatedDocument.getDocFileContentType()).isEqualTo(DEFAULT_DOC_FILE_CONTENT_TYPE);

        // Validate the RelatedDocument in Elasticsearch
        RelatedDocument relatedDocumentEs = relatedDocumentSearchRepository.findOne(testRelatedDocument.getId());
        assertThat(relatedDocumentEs).isEqualToIgnoringGivenFields(testRelatedDocument);
    }

    @Test
    @Transactional
    public void createRelatedDocumentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = relatedDocumentRepository.findAll().size();

        // Create the RelatedDocument with an existing ID
        relatedDocument.setId(1L);
        RelatedDocumentDTO relatedDocumentDTO = relatedDocumentMapper.toDto(relatedDocument);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelatedDocumentMockMvc.perform(post("/api/related-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatedDocumentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RelatedDocument in the database
        List<RelatedDocument> relatedDocumentList = relatedDocumentRepository.findAll();
        assertThat(relatedDocumentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRelatedDocuments() throws Exception {
        // Initialize the database
        relatedDocumentRepository.saveAndFlush(relatedDocument);

        // Get all the relatedDocumentList
        restRelatedDocumentMockMvc.perform(get("/api/related-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatedDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].docName").value(hasItem(DEFAULT_DOC_NAME.toString())))
            .andExpect(jsonPath("$.[*].docFileContentType").value(hasItem(DEFAULT_DOC_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].docFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOC_FILE))));
    }

    @Test
    @Transactional
    public void getRelatedDocument() throws Exception {
        // Initialize the database
        relatedDocumentRepository.saveAndFlush(relatedDocument);

        // Get the relatedDocument
        restRelatedDocumentMockMvc.perform(get("/api/related-documents/{id}", relatedDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(relatedDocument.getId().intValue()))
            .andExpect(jsonPath("$.docName").value(DEFAULT_DOC_NAME.toString()))
            .andExpect(jsonPath("$.docFileContentType").value(DEFAULT_DOC_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.docFile").value(Base64Utils.encodeToString(DEFAULT_DOC_FILE)));
    }

    @Test
    @Transactional
    public void getNonExistingRelatedDocument() throws Exception {
        // Get the relatedDocument
        restRelatedDocumentMockMvc.perform(get("/api/related-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRelatedDocument() throws Exception {
        // Initialize the database
        relatedDocumentRepository.saveAndFlush(relatedDocument);
        relatedDocumentSearchRepository.save(relatedDocument);
        int databaseSizeBeforeUpdate = relatedDocumentRepository.findAll().size();

        // Update the relatedDocument
        RelatedDocument updatedRelatedDocument = relatedDocumentRepository.findOne(relatedDocument.getId());
        // Disconnect from session so that the updates on updatedRelatedDocument are not directly saved in db
        em.detach(updatedRelatedDocument);
        updatedRelatedDocument
            .docName(UPDATED_DOC_NAME)
            .docFile(UPDATED_DOC_FILE)
            .docFileContentType(UPDATED_DOC_FILE_CONTENT_TYPE);
        RelatedDocumentDTO relatedDocumentDTO = relatedDocumentMapper.toDto(updatedRelatedDocument);

        restRelatedDocumentMockMvc.perform(put("/api/related-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatedDocumentDTO)))
            .andExpect(status().isOk());

        // Validate the RelatedDocument in the database
        List<RelatedDocument> relatedDocumentList = relatedDocumentRepository.findAll();
        assertThat(relatedDocumentList).hasSize(databaseSizeBeforeUpdate);
        RelatedDocument testRelatedDocument = relatedDocumentList.get(relatedDocumentList.size() - 1);
        assertThat(testRelatedDocument.getDocName()).isEqualTo(UPDATED_DOC_NAME);
        assertThat(testRelatedDocument.getDocFile()).isEqualTo(UPDATED_DOC_FILE);
        assertThat(testRelatedDocument.getDocFileContentType()).isEqualTo(UPDATED_DOC_FILE_CONTENT_TYPE);

        // Validate the RelatedDocument in Elasticsearch
        RelatedDocument relatedDocumentEs = relatedDocumentSearchRepository.findOne(testRelatedDocument.getId());
        assertThat(relatedDocumentEs).isEqualToIgnoringGivenFields(testRelatedDocument);
    }

    @Test
    @Transactional
    public void updateNonExistingRelatedDocument() throws Exception {
        int databaseSizeBeforeUpdate = relatedDocumentRepository.findAll().size();

        // Create the RelatedDocument
        RelatedDocumentDTO relatedDocumentDTO = relatedDocumentMapper.toDto(relatedDocument);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRelatedDocumentMockMvc.perform(put("/api/related-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(relatedDocumentDTO)))
            .andExpect(status().isCreated());

        // Validate the RelatedDocument in the database
        List<RelatedDocument> relatedDocumentList = relatedDocumentRepository.findAll();
        assertThat(relatedDocumentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRelatedDocument() throws Exception {
        // Initialize the database
        relatedDocumentRepository.saveAndFlush(relatedDocument);
        relatedDocumentSearchRepository.save(relatedDocument);
        int databaseSizeBeforeDelete = relatedDocumentRepository.findAll().size();

        // Get the relatedDocument
        restRelatedDocumentMockMvc.perform(delete("/api/related-documents/{id}", relatedDocument.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean relatedDocumentExistsInEs = relatedDocumentSearchRepository.exists(relatedDocument.getId());
        assertThat(relatedDocumentExistsInEs).isFalse();

        // Validate the database is empty
        List<RelatedDocument> relatedDocumentList = relatedDocumentRepository.findAll();
        assertThat(relatedDocumentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchRelatedDocument() throws Exception {
        // Initialize the database
        relatedDocumentRepository.saveAndFlush(relatedDocument);
        relatedDocumentSearchRepository.save(relatedDocument);

        // Search the relatedDocument
        restRelatedDocumentMockMvc.perform(get("/api/_search/related-documents?query=id:" + relatedDocument.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relatedDocument.getId().intValue())))
            .andExpect(jsonPath("$.[*].docName").value(hasItem(DEFAULT_DOC_NAME.toString())))
            .andExpect(jsonPath("$.[*].docFileContentType").value(hasItem(DEFAULT_DOC_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].docFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_DOC_FILE))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatedDocument.class);
        RelatedDocument relatedDocument1 = new RelatedDocument();
        relatedDocument1.setId(1L);
        RelatedDocument relatedDocument2 = new RelatedDocument();
        relatedDocument2.setId(relatedDocument1.getId());
        assertThat(relatedDocument1).isEqualTo(relatedDocument2);
        relatedDocument2.setId(2L);
        assertThat(relatedDocument1).isNotEqualTo(relatedDocument2);
        relatedDocument1.setId(null);
        assertThat(relatedDocument1).isNotEqualTo(relatedDocument2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelatedDocumentDTO.class);
        RelatedDocumentDTO relatedDocumentDTO1 = new RelatedDocumentDTO();
        relatedDocumentDTO1.setId(1L);
        RelatedDocumentDTO relatedDocumentDTO2 = new RelatedDocumentDTO();
        assertThat(relatedDocumentDTO1).isNotEqualTo(relatedDocumentDTO2);
        relatedDocumentDTO2.setId(relatedDocumentDTO1.getId());
        assertThat(relatedDocumentDTO1).isEqualTo(relatedDocumentDTO2);
        relatedDocumentDTO2.setId(2L);
        assertThat(relatedDocumentDTO1).isNotEqualTo(relatedDocumentDTO2);
        relatedDocumentDTO1.setId(null);
        assertThat(relatedDocumentDTO1).isNotEqualTo(relatedDocumentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(relatedDocumentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(relatedDocumentMapper.fromId(null)).isNull();
    }
}
