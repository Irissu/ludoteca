package com.ccsw.tutorial.exception;

//@ResponseStatus(HttpStatus.BAD_REQUEST)

public class ClientNameExists extends RuntimeException {

    public ClientNameExists(String message) {
        super(message);
    }
}
