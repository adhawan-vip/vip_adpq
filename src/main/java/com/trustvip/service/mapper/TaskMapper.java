package com.trustvip.service.mapper;

import com.trustvip.domain.*;
import com.trustvip.service.dto.TaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Task and its DTO TaskDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskOwnerMapper.class, ArticleMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "owner.name", target = "ownerName")
    @Mapping(source = "article.id", target = "articleId")
    @Mapping(source = "article.articleName", target = "articleArticleName")
    TaskDTO toDto(Task task);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "articleId", target = "article")
    Task toEntity(TaskDTO taskDTO);

    default Task fromId(Long id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }
}
