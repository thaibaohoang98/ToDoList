package com.springboot.todolist.repository;

import com.springboot.todolist.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Work Repository
 */
public interface WorkRepository extends JpaRepository<Work, Long>
{
    @Query("select w from Work w where w.isDeleted = false")
    Page<Work> findAll(Pageable pageable);

    @Query("select w from Work w where w.id = :id and w.isDeleted = false")
    Optional<Work> findById(@Param("id") Long id);

    @Modifying
    @Query("update Work w set w.isDeleted = true where w.id = :id")
    void deleteById(@Param("id") Long id);
}
