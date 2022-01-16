package com.springboot.todolist.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Status
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Status
{
    /**
     * Planing Status
     */
    PLANNING(0),

    /**
     * Doing Status
     */
    DOING(1),

    /**
     * Complete Status
     */
    COMPLETE(2);

    @Getter(onMethod = @__(@JsonValue))
    private final Integer value;

    /**
     * Get the enumerator from the string that correspond to the enumerator.
     *
     * @param value String corresponding to the enumerator
     * @return Enumerator
     * @throws IllegalArgumentException Thrown if the enumerator corresponding to the number does not exist.
     */
    @JsonCreator
    public static Status of(Integer value) {
        return Arrays.stream(values())
                .filter(v -> v.getValue().equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Status = '" + value + "' is not supported."));
    }
}
