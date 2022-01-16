package com.springboot.todolist.common.exception;

import com.springboot.todolist.common.error.ErrorCode;

/**
 * Exception class that notifies that the target resource does not exist
 */
public class ResourceNotFoundException extends CommonException
{
    private static final long serialVersionUID = 1L;
    private static final ErrorCode ERROR_CODE = ErrorCode.RESOURCE_NOT_FOUND;
    private static final String MESSAGE_TEMPLATE = "Resource '%s' specified id = '%s' does not exists.";

    public ResourceNotFoundException(Class<?> resourceClass, Long id) {
        super(ERROR_CODE, MESSAGE_TEMPLATE, resourceClass.getSimpleName(), id);
    }
}
