package com.trustvip.service.impl;

import com.trustvip.service.RelatedDocumentService;
import com.trustvip.domain.RelatedDocument;
import com.trustvip.repository.RelatedDocumentRepository;
import com.trustvip.repository.search.RelatedDocumentSearchRepository;
import com.trustvip.service.dto.RelatedDocumentDTO;
import com.trustvip.service.mapper.RelatedDocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing RelatedDocument.
 */
@Service
@Transactional
public class RelatedDocumentServiceImpl implements RelatedDocumentService {

    private final Logger log = LoggerFactory.getLogger(RelatedDocumentServiceImpl.class);

    private final RelatedDocumentRepository relatedDocumentRepository;

    private final RelatedDocumentMapper relatedDocumentMapper;

    private final RelatedDocumentSearchRepository relatedDocumentSearchRepository;

    public RelatedDocumentServiceImpl(RelatedDocumentRepository relatedDocumentRepository, RelatedDocumentMapper relatedDocumentMapper, RelatedDocumentSearchRepository relatedDocumentSearchRepository) {
        this.relatedDocumentRepository = relatedDocumentRepository;
        this.relatedDocumentMapper = relatedDocumentMapper;
        this.relatedDocumentSearchRepository = relatedDocumentSearchRepository;
    }

    /**
     * Save a relatedDocument.
     *
     * @param relatedDocumentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RelatedDocumentDTO save(RelatedDocumentDTO relatedDocumentDTO) {
        log.debug("Request to save RelatedDocument : {}", relatedDocumentDTO);
        RelatedDocument relatedDocument = relatedDocumentMapper.toEntity(relatedDocumentDTO);
        relatedDocument = relatedDocumentRepository.save(relatedDocument);
        RelatedDocumentDTO result = relatedDocumentMapper.toDto(relatedDocument);
        relatedDocumentSearchRepository.save(relatedDocument);
        return result;
    }

    /**
     * Get all the relatedDocuments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RelatedDocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelatedDocuments");
        return relatedDocumentRepository.findAll(pageable)
            .map(relatedDocumentMapper::toDto);
    }

    /**
     * Get one relatedDocument by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RelatedDocumentDTO findOne(Long id) {
        log.debug("Request to get RelatedDocument : {}", id);
        RelatedDocument relatedDocument = relatedDocumentRepository.findOne(id);
        return relatedDocumentMapper.toDto(relatedDocument);
    }

    /**
     * Delete the relatedDocument by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelatedDocument : {}", id);
        relatedDocumentRepository.delete(id);
        relatedDocumentSearchRepository.delete(id);
    }

    /**
     * Search for the relatedDocument corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RelatedDocumentDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RelatedDocuments for query {}", query);
        Page<RelatedDocument> result = relatedDocumentSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(relatedDocumentMapper::toDto);
    }
}
