package com.trustvip.repository.search;

import com.trustvip.domain.TaskOwner;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TaskOwner entity.
 */
public interface TaskOwnerSearchRepository extends ElasticsearchRepository<TaskOwner, Long> {
}
