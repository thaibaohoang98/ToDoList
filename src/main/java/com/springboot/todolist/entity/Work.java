package com.springboot.todolist.entity;

import com.springboot.todolist.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

/**
 * Work entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@With
@Entity
@Table(name = "work")
@EntityListeners(AuditingEntityListener.class)
public class Work
{
    /**
     * Work ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Work Name
     */
    @Column(name = "work_name")
    private String workName;

    /**
     * Starting Date
     */
    @Column(name = "starting_date")
    private LocalDate startingDate;

    /**
     * Ending Date
     */
    @Column(name = "ending_date")
    private LocalDate endingDate;

    /**
     * Status Of Work
     */
    @Column(name = "status")
    private Status status;

    /**
     * Registered person
     */
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    /**
     * Registered Date
     */
    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    /**
     * Changer
     */
    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    /**
     * Changed date
     */
    @Column(name = "modified_at")
    @LastModifiedDate
    private Date modifiedAt;

    /**
     * Delete flag
     */
    @Column(name = "is_deleted")
    @Accessors(fluent = true)
    private Boolean isDeleted;
}
