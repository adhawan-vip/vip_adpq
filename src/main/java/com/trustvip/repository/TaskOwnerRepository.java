package com.trustvip.repository;

import com.trustvip.domain.TaskOwner;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TaskOwner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskOwnerRepository extends JpaRepository<TaskOwner, Long> {

}
