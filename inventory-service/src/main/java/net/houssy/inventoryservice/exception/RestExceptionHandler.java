package net.houssy.inventoryservice.exception;

import net.houssy.inventoryservice.dto.ErrorResponse;
import net.houssy.inventoryservice.exception.exceptions.BusinessException;
import net.houssy.inventoryservice.exception.exceptions.DuplicatedResourceException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorMessage=ErrorResponse.builder()
                .message(ex.getMessage())
                .Status(HttpStatus.NOT_FOUND.value())
                .timestamp( LocalDateTime.now())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicatedResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedResourceException(DuplicatedResourceException ex) {
        ErrorResponse errorMessage=ErrorResponse.builder()
                .message(ex.getMessage())
                .Status(HttpStatus.CONFLICT.value())
                .timestamp( LocalDateTime.now())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse errorMessage=ErrorResponse.builder()
                .message(ex.getMessage())
                .Status(HttpStatus.BAD_REQUEST.value())
                .timestamp( LocalDateTime.now())
                .build();
        return  new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
