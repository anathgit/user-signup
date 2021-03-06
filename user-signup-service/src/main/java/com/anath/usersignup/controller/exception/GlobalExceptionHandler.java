package com.anath.usersignup.controller.exception;

import com.anath.usersignup.dao.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, errors), headers, HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(value
            = { UserNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(
            UserNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex,"Requested User not found",
                                       new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private final ApiError message(final HttpStatus httpStatus, Map<String, String> errors) {

        return new ApiError(httpStatus.value(), errors);
    }
}
