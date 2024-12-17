package com.ccsw.tutorial.exception;

public class LoanNotValidException extends RuntimeException {

    public LoanNotValidException(String message) {
        super(message);
    }
}
