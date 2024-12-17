package com.ccsw.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientNameExists.class)
    public ResponseEntity<ErrorResponse> handleClientException(ClientNameExists e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "CLIENT_NAME_EXISTS");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("An unexpected error occurred", "GENERAL_ERROR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFoundException(LoanNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "LOAN_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(LoanNotValidDateException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotValidDateException(LoanNotValidDateException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "NOT_VALID_DATE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LoanNotValidException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotValidException(LoanNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "NOT_VALID_LOAN");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAuthorNotFoundException(AuthorNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "AUTHOR_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGameNotFoundException(GameNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "GAME_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "CATEGORY_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFoundException(ClientNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "CLIENT_NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidReturnDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidReturnDateException(InvalidReturnDateException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), "INVALID_RETURN_DATE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}