package com.springboot.todolist.common.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * API Error Response
 */
@Builder
@AllArgsConstructor
public class ApiErrorResponse
{
    /**
     * Error code
     */
    @Getter
    private final ErrorCode code;

    /**
     * Error message
     */
    @Getter
    private final String message;

    /**
     * Error cause
     */
    private final Throwable cause;
}
