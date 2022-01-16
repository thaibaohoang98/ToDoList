package com.springboot.todolist.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

/**
 * Sort parameter
 */
@Getter
@Setter
public class SortParam
{
    /**
     * Sort by column (default id)
     */
    private String sortBy = "id";

    /**
     * Sort direction (default ascending)
     */
    private Direction direction = Direction.ASC;
}
