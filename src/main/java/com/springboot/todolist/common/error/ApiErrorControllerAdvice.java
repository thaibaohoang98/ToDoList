package com.springboot.todolist.common.error;

import com.springboot.todolist.common.exception.DefaultExceptionTranslator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * API Error Handler
 */
@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ApiErrorControllerAdvice
{
    private final DefaultExceptionTranslator exceptionTranslator;

    /**
     * Convert any exception thrown by RestController into an error response.
     *
     * @param cause Any exception
     * @return Error response entity
     * @see ExceptionHandler
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception cause) {
        log.trace("ApiErrorControllerAdvice caught exception", cause);
        return exceptionTranslator.translate(cause);
    }
}
