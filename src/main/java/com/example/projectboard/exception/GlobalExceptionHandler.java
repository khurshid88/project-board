package com.example.projectboard.exception;

import com.example.projectboard.model.common.Header;
import com.example.projectboard.model.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//https://salithachathuranga94.medium.com/validation-and-exception-handling-in-spring-boot-51597b580ffd

/**
 * All exceptions can be handled here. Responses are based on same style but
 * client gets different status codes such as 200, 404, 405 etc.
 * And what about if client and server deliver the data with same structure?
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Header<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return Header.error("Resource Not Found");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Header<?> handleValidationErrors(MethodArgumentNotValidException exception) {
        return Header.error(exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public Header<?> handleMethodNotAllowed(Exception exception) {
        return Header.error(exception.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Header<?> handleGeneralException(RuntimeException exception) {
        return Header.error(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Header<?> handleRuntimeException(RuntimeException exception) {
        return Header.error(exception.getMessage());
    }
}
