package com.springboot.todolist.common.exception;

import com.springboot.todolist.common.error.ErrorCode;

/**
 * Exception class notifying input parameter validation error
 */
public class ValidationErrorException extends CommonException
{
    private static final long serialVersionUID = 1L;
    private static final ErrorCode ERROR_CODE = ErrorCode.VALIDATION_ERROR;

    /**
     * Construct a new exception object using the specified detail message template argument
     *
     * @param messageTemplate Detailed message template (format string of exception detailed message)
     * @param args Arguments referenced by the format specifier in the detail message template
     */
    public ValidationErrorException(String messageTemplate, Object... args) {
        super(ERROR_CODE, messageTemplate, args);
    }
}
