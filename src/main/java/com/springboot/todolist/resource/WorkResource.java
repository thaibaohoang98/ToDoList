package com.springboot.todolist.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.todolist.domain.Status;
import com.springboot.todolist.entity.Work;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@Getter
@Builder(toBuilder = true)
@With
@ToString
public class WorkResource
{
    /**
     * Work ID
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;

    /**
     * Work Name
     */
    @NotBlank
    @Size(max = 255)
    private final String workName;

    /**
     * Starting Date
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startingDate;

    /**
     * Ending Date
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endingDate;

    /**
     * Status Of Work
     */
    private final Status status;

    /**
     * Registered person
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final String createdBy;

    /**
     * Registered Date
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Date createAt;

    /**
     * Changer
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final String modifiedBy;

    /**
     * Changed date
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Date modifiedAt;

    /**
     * Delete flag
     */
    private final Boolean isDeleted;

    /**
     * Build a work resource using the specified work entity
     *
     * @param entity Work entity
     */
    public WorkResource(Work entity) {
        this.id = entity.getId();
        this.workName = entity.getWorkName();
        this.startingDate = entity.getStartingDate();
        this.endingDate = entity.getEndingDate();
        this.status = entity.getStatus();
        this.createdBy = entity.getCreatedBy();
        this.createAt = entity.getCreatedAt();
        this.modifiedBy = entity.getModifiedBy();
        this.modifiedAt = entity.getModifiedAt();
        this.isDeleted = entity.isDeleted();
    }

    /**
     * Convert a work resource to a work entity
     *
     * @return Work entity
     */
    public Work toEntity() {
        return Work.builder()
                .id(id)
                .workName(workName)
                .startingDate(startingDate)
                .endingDate(endingDate)
                .status(status)
                .createdBy(createdBy)
                .createdAt(createAt)
                .modifiedBy(modifiedBy)
                .modifiedAt(modifiedAt)
                .isDeleted(isDeleted)
                .build();
    }
}
