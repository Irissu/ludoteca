package com.ccsw.tutorial.exception;

//@ResponseStatus(HttpStatus.BAD_REQUEST)

public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }
}
