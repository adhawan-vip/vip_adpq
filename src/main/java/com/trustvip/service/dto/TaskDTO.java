package com.trustvip.service.dto;


import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.trustvip.domain.enumeration.TaskStatus;

/**
 * A DTO for the Task entity.
 */
public class TaskDTO implements Serializable {

    private Long id;

    @NotNull
    private String taskName;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private String description;

    private TaskStatus status;

    private Long ownerId;

    private String ownerName;

    private Long articleId;

    private String articleArticleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long taskOwnerId) {
        this.ownerId = taskOwnerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String taskOwnerName) {
        this.ownerName = taskOwnerName;
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

        TaskDTO taskDTO = (TaskDTO) o;
        if(taskDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
            "id=" + getId() +
            ", taskName='" + getTaskName() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
