package com.ccsw.tutorial.exception;

public class ClientNameExists extends RuntimeException {

    public ClientNameExists(String message) {
        super(message);
    }
}
