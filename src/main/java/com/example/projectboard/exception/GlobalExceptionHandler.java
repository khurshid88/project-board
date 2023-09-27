package com.example.projectboard.exception;

import com.example.projectboard.model.dto.CustomResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

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
    public CustomResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
        return CustomResponse.error("Resource Not Found");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public CustomResponse handleValidationErrors(MethodArgumentNotValidException exception) {
        return CustomResponse.error(exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public CustomResponse handleMethodNotAllowed(Exception exception) {
        return CustomResponse.error(exception.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomResponse handleGeneralException(RuntimeException exception) {
        return CustomResponse.error(exception.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomResponse handleRuntimeException(RuntimeException exception) {
        return CustomResponse.error(exception.getMessage());
    }
}
