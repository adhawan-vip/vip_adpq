package com.trustvip.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RelatedDocument.
 */
@Entity
@Table(name = "related_document")
@Document(indexName = "relateddocument")
public class RelatedDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doc_name")
    private String docName;

    @Lob
    @Column(name = "doc_file")
    private byte[] docFile;

    @Column(name = "doc_file_content_type")
    private String docFileContentType;

    @ManyToOne(optional = false)
    @NotNull
    private Article article;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public RelatedDocument docName(String docName) {
        this.docName = docName;
        return this;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public RelatedDocument docFile(byte[] docFile) {
        this.docFile = docFile;
        return this;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }

    public String getDocFileContentType() {
        return docFileContentType;
    }

    public RelatedDocument docFileContentType(String docFileContentType) {
        this.docFileContentType = docFileContentType;
        return this;
    }

    public void setDocFileContentType(String docFileContentType) {
        this.docFileContentType = docFileContentType;
    }

    public Article getArticle() {
        return article;
    }

    public RelatedDocument article(Article article) {
        this.article = article;
        return this;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RelatedDocument relatedDocument = (RelatedDocument) o;
        if (relatedDocument.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), relatedDocument.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RelatedDocument{" +
            "id=" + getId() +
            ", docName='" + getDocName() + "'" +
            ", docFile='" + getDocFile() + "'" +
            ", docFileContentType='" + getDocFileContentType() + "'" +
            "}";
    }
}
