package com.springboot.todolist.common.exception;

import com.springboot.todolist.common.error.ErrorCode;
import lombok.Getter;

/**
 * Base class for exceptions
 */
public class CommonException extends RuntimeException
{
    @Getter
    private final ErrorCode code;

    /**
     * Constructs a new exception object with the specified error code, message format, and arguments
     *
     * @param code Error code
     * @param format Message format string
     * @param args Arguments referenced by message format specifiers
     */
    public CommonException(ErrorCode code, String format, Object... args) {
        super(String.format(format, args));
        this.code = code;
    }

    /**
     * Builds a new exception object with the specified error code, cause, message format, and arguments
     * The detail message associated with cause is not automatically integrated into the detail message for this exception
     *
     * @param code Error code
     * @param cause Cause
     * @param format Message format string
     * @param args Arguments referenced by message format specifiers
     */
    public CommonException(ErrorCode code, Throwable cause, String format, Object... args) {
        super(String.format(format, args), cause);
        this.code = code;
    }
}
