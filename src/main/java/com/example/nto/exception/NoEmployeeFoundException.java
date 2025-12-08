package com.example.nto.exception;

public class NoEmployeeFoundException extends  RuntimeException{
    public NoEmployeeFoundException(String msg) { super(msg); }
}
