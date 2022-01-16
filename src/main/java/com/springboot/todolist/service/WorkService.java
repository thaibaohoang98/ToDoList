package com.springboot.todolist.service;

import com.springboot.todolist.common.domain.PaginationParam;
import com.springboot.todolist.common.domain.ResultSet;
import com.springboot.todolist.common.domain.SortParam;
import com.springboot.todolist.entity.Work;
import com.springboot.todolist.resource.WorkResource;

import java.util.List;
import java.util.Optional;

/**
 * Work service
 */
public interface WorkService
{
    /**
     * Get a list of work resources
     *
     * @param pagination Pagination parameter
     * @param sort Sort parameter
     * @return Work resource list
     */
    ResultSet<WorkResource> getWorkResourceList(PaginationParam pagination, SortParam sort);

    /**
     * Get work resource
     *
     * @param id Work ID
     * @return Work resource
     */
    Optional<WorkResource> getWorkResource(Long id);

    /**
     * Work resource creation
     *
     * @param workResource Work resource
     * @return Created work resource
     */
    WorkResource createWorkResource(WorkResource workResource);

    /**
     * Update work resource
     *
     * @param id Work ID
     * @param workResource Work resource
     * @return a
     */
    WorkResource updateWorkResource(Long id, WorkResource workResource);

    /**
     * Delete work resource
     *
     * @param id Work ID
     */
    void deleteWorkResource(Long id);

    /**
     * Validate staring date and ending date of work resource
     *
     * @param workResource Work resource
     */
    void validateDateApply(WorkResource workResource);

    /**
     * Convert a work entity to a work resource
     *
     * @param entity Work entity
     * @return Work resource
     */
    WorkResource convertEntityToResource(Work entity);

    /**
     * Converts a list of work entities to a list of work resources
     *
     * @param entities List of work entities
     * @return List of work resources
     */
    List<WorkResource> convertEntitiesToResources(List<Work> entities);
}
