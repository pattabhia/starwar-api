package com.devcode.starwar.api.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage exceptionResponse = new ErrorMessage(ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TechnicalException.class)
    public final ResponseEntity<ErrorMessage> handleTechnicalException(TechnicalException ex, WebRequest request) {
        ErrorMessage exceptionResponse = new ErrorMessage(ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequiredFieldsMissingException.class)
    public final ResponseEntity<ErrorMessage> handleMissingFieldsException(RequiredFieldsMissingException ex, WebRequest request) {
        ErrorMessage exceptionResponse = new ErrorMessage(ex.getMessage());
        log.error(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
