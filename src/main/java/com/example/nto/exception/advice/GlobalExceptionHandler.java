package com.example.nto.exception.advice;

import com.example.nto.exception.AlreadyBookedException;
import com.example.nto.exception.EmptyCodeException;
import com.example.nto.exception.NoSuchCodeException;
import com.example.nto.exception.NoSuchPlaceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// TODO: реализовать 'что-то пошло не так'
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchCodeException.class)
    public ResponseEntity<String> handleNoSuchCodeException(NoSuchCodeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchPlaceException.class)
    public ResponseEntity<String> handleNoSuchPlaceException(NoSuchPlaceException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyCodeException.class)
    public ResponseEntity<String> handleEmptyCodeException(EmptyCodeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyBookedException.class)
    public ResponseEntity<String> handleAlreadyBookedException(AlreadyBookedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
