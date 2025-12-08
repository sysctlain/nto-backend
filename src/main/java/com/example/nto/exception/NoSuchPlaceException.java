package com.example.nto.exception;

public class NoSuchPlaceException extends RuntimeException {
    public NoSuchPlaceException(String message) {
        super(message);
    }
}
