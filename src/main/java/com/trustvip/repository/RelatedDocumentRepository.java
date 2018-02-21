package com.trustvip.repository;

import com.trustvip.domain.RelatedDocument;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RelatedDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelatedDocumentRepository extends JpaRepository<RelatedDocument, Long> {

}
