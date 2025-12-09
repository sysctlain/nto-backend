package com.example.nto.exception;

public class AlreadyHasBookingException extends RuntimeException {
    public AlreadyHasBookingException(String message) {
        super(message);
    }
}
