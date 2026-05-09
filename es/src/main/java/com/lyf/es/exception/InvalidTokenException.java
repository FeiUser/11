package com.lyf.es.exception;

public class InvalidTokenException extends Exception{

    public InvalidTokenException() {
        super("token is expire or invalid");
    }
}
