package com.trustvip.repository.search;

import com.trustvip.domain.RelatedDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RelatedDocument entity.
 */
public interface RelatedDocumentSearchRepository extends ElasticsearchRepository<RelatedDocument, Long> {
}
