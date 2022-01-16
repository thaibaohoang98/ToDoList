package com.springboot.todolist.common.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Error code
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode
{
    /**
     * 400 Bad Request
     */
    BAD_REQUEST("E400000"),

    /**
     * This error code indicates that the server received an invalid input parameter.
     */
    VALIDATION_ERROR("E400001"),

    /**
     * 404 Not Found
     */
    NOT_FOUND("E404000"),

    /**
     * Error code indicating that the resource (or entity) specified by the input parameter does not exist.
     */
    RESOURCE_NOT_FOUND("E404001"),

    /**
     * 405 Method Not Allowed
     */
    METHOD_NOT_ALLOWED("E405000"),

    /**
     * 500 Internal Server Error
     */
    INTERNAL_SERVER_ERROR("E500000");

    @Getter(onMethod = @__(@JsonValue))
    private final String value;

    @JsonCreator
    public static ErrorCode of(String value) {
        return Arrays.stream(values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ErrorCode = '" + value + "' is not supported."));
    }
}
