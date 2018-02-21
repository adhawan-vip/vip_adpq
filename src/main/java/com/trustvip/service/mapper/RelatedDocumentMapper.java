package com.trustvip.service.mapper;

import com.trustvip.domain.*;
import com.trustvip.service.dto.RelatedDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RelatedDocument and its DTO RelatedDocumentDTO.
 */
@Mapper(componentModel = "spring", uses = {ArticleMapper.class})
public interface RelatedDocumentMapper extends EntityMapper<RelatedDocumentDTO, RelatedDocument> {

    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "article.articleName", target = "articleArticleName")
    RelatedDocumentDTO toDto(RelatedDocument relatedDocument);

    @Mapping(source = "articleId", target = "article")
    RelatedDocument toEntity(RelatedDocumentDTO relatedDocumentDTO);

    default RelatedDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelatedDocument relatedDocument = new RelatedDocument();
        relatedDocument.setId(id);
        return relatedDocument;
    }
}
