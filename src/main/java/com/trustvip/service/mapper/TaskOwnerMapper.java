package com.trustvip.service.mapper;

import com.trustvip.domain.*;
import com.trustvip.service.dto.TaskOwnerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TaskOwner and its DTO TaskOwnerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskOwnerMapper extends EntityMapper<TaskOwnerDTO, TaskOwner> {



    default TaskOwner fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaskOwner taskOwner = new TaskOwner();
        taskOwner.setId(id);
        return taskOwner;
    }
}
