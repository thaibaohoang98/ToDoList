package com.springboot.todolist.common.exception;

import com.springboot.todolist.common.error.ErrorCode;
import com.springboot.todolist.common.error.ApiErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * Default ExceptionTranslator
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Component
public class DefaultExceptionTranslator
{
    public ResponseEntity<ApiErrorResponse> translate(Exception cause) {
        ErrorCode code = (cause instanceof CommonException) ? ((CommonException) cause).getCode() : getErrorCode(cause);
        HttpStatus status = HttpStatus.valueOf(Integer.parseInt(code.getValue().substring(1, 4)));
        ApiErrorResponse body = ApiErrorResponse.builder()
                .code(code)
                .message(cause.getMessage())
                .cause(cause)
                .build();
        return ResponseEntity.status(status)
                .body(body);
    }

    private ErrorCode getErrorCode(Exception cause) {
        try {
            throw cause;
        } catch (MethodArgumentNotValidException
                | MethodArgumentTypeMismatchException
                | ConstraintViolationException
                | IllegalArgumentException
                | HttpMessageConversionException e) {
            return ErrorCode.VALIDATION_ERROR;
        } catch (NoHandlerFoundException e) {
            return ErrorCode.NOT_FOUND;
        } catch (HttpRequestMethodNotSupportedException e) {
            return ErrorCode.METHOD_NOT_ALLOWED;
        } catch (Exception e) {
            return ErrorCode.INTERNAL_SERVER_ERROR;
        }
    }
}
