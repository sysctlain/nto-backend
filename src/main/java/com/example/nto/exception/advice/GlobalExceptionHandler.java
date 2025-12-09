package com.example.nto.exception.advice;

import com.example.nto.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoEmployeeFoundException.class)
    public ResponseEntity<String> handleNoEmployeeFoundException(NoEmployeeFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }

    @ExceptionHandler(NoPlaceFoundException.class)
    public ResponseEntity<String> handleNoPlaceFoundException(NoPlaceFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @ExceptionHandler(EmptyCodeException.class)
    public ResponseEntity<String> handleEmptyCodeException(EmptyCodeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @ExceptionHandler(AlreadyBookedException.class)
    public ResponseEntity<String> handleAlreadyBookedException(AlreadyBookedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("");
    }

    @ExceptionHandler(AlreadyHasBookingException.class)
    public ResponseEntity<String> handleAlreadyHasBookingException(AlreadyHasBookingException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("");
    }
}
