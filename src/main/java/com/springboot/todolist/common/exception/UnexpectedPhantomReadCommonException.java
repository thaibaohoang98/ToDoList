package com.springboot.todolist.common.exception;

import com.springboot.todolist.common.error.ErrorCode;

/**
 * Exception class that signals that an unexpected phantom read has occurred
 */
public class UnexpectedPhantomReadCommonException extends CommonException
{
    private static final long serialVersionUID = 1L;
    private static final ErrorCode ERROR_CODE = ErrorCode.INTERNAL_SERVER_ERROR;
    private static final String MESSAGE_TEMPLATE = "Unexpected phantom read has occurred.";

    /**
     * Build a new exception object
     */
    public UnexpectedPhantomReadCommonException() {
        super(ERROR_CODE, MESSAGE_TEMPLATE);
    }
}
