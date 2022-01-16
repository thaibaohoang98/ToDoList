package com.springboot.todolist.controller;

import com.springboot.todolist.common.domain.PaginationParam;
import com.springboot.todolist.common.domain.ResultSet;
import com.springboot.todolist.common.domain.SortParam;
import com.springboot.todolist.common.exception.ResourceNotFoundException;
import com.springboot.todolist.resource.WorkResource;
import com.springboot.todolist.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.relativeTo;

/**
 * Work Controller
 */
@RestController
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkController
{
    private static final String RESOURCE_ID_HEADER = "Resource-Id";

    /**
     * Collection Resource URI
     */
    private static final String COLLECTION_RESOURCE_URI = "/works";

    /**
     * Item tag collection resource URI
     */
    private static final String MEMBER_RESOURCE_URI = COLLECTION_RESOURCE_URI + "/{id}";

    /**
     * Work service
     */
    private final WorkService workService;

    /**
     * Response API get work resource list
     *
     * @param pagination Pagination parameter
     * @param sort Sort parameter
     * @return Response API get work resource list
     */
    @GetMapping(COLLECTION_RESOURCE_URI)
    public ResponseEntity<ResultSet<WorkResource>> getWorkResourceList(
            PaginationParam pagination,
            SortParam sort) {
        return ResponseEntity.ok(workService.getWorkResourceList(pagination, sort));
    }

    /**
     * API get work resource
     *
     * @param id Work ID
     * @return Response API get work resource
     */
    @GetMapping(MEMBER_RESOURCE_URI)
    public ResponseEntity<WorkResource> getWorkResource(
            @PathVariable("id") Long id) {
        return workService.getWorkResource(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(WorkResource.class, id));
    }

    /**
     * API create work resource
     *
     * @param workResource work resource
     * @return Response API create work resource
     */
    @PostMapping(COLLECTION_RESOURCE_URI)
    public ResponseEntity<Void> createWorkResource(
            @Valid @RequestBody WorkResource workResource,
            UriComponentsBuilder uriBuilder) {
        Long id = workService.createWorkResource(workResource).getId();
        URI uri = relativeTo(uriBuilder)
                .withMethodCall(on(getClass()).getWorkResource(id))
                .build()
                .encode()
                .toUri();
        return ResponseEntity.created(uri)
                .header(RESOURCE_ID_HEADER, id.toString())
                .build();
    }

    /**
     * API update work resource
     *
     * @param id Work ID
     * @param workResource Work resource
     * @return Response API update work resource
     */
    @PutMapping(MEMBER_RESOURCE_URI)
    public ResponseEntity<WorkResource> updateWorkResource(
            @PathVariable("id") Long id,
            @Valid @RequestBody WorkResource workResource) {
        WorkResource updatedWorkResource = workService.updateWorkResource(id, workResource);
        return ResponseEntity.ok(updatedWorkResource);
    }

    /**
     * API delete work resource
     *
     * @param id Work ID
     * @return Response API delete work resource
     */
    @DeleteMapping(MEMBER_RESOURCE_URI)
    public ResponseEntity<Void> deleteWorkResource(
            @PathVariable("id") Long id) {
        workService.deleteWorkResource(id);
        return ResponseEntity.noContent().build();
    }
}
