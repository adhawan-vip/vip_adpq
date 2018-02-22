package com.trustvip.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the RelatedDocument entity.
 */
public class RelatedDocumentDTO implements Serializable {

    private Long id;

    private String docName;

    @Lob
    private byte[] docFile;
    private String docFileContentType;

    private Long articleId;

    private String articleArticleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }

    public String getDocFileContentType() {
        return docFileContentType;
    }

    public void setDocFileContentType(String docFileContentType) {
        this.docFileContentType = docFileContentType;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleArticleName() {
        return articleArticleName;
    }

    public void setArticleArticleName(String articleArticleName) {
        this.articleArticleName = articleArticleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RelatedDocumentDTO relatedDocumentDTO = (RelatedDocumentDTO) o;
        if(relatedDocumentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relatedDocumentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelatedDocumentDTO{" +
            "id=" + getId() +
            ", docName='" + getDocName() + "'" +
            ", docFile='" + getDocFile() + "'" +
            "}";
    }
}
