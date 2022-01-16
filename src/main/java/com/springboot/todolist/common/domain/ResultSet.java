package com.springboot.todolist.common.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.io.Serializable;
import java.util.List;

/**
 * Search result set object
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Value
@Builder
@With
public class ResultSet<T> implements Serializable
{
    /**
     * Search results (list of data)
     */
    List<T> data;

    /**
     * Number of hits in search results
     */
    long count;
}
