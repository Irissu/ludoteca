package com.ccsw.tutorial.exception;

public class LoanNotValidDateException extends RuntimeException {

    public LoanNotValidDateException(String message) {
        super(message);
    }
}
