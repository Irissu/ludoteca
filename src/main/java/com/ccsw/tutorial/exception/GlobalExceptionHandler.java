package com.ccsw.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //    @ExceptionHandler(ClientNameExists.class)
    //    public ResponseEntity<String> handleClientException(ClientNameExists e) {
    //        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //
    //    }

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

    // VERSION CON MENSAJE PERSONALIZADO
    //    @ExceptionHandler(ClientNameExists.class)
    //         public ResponseEntity<ErrorResponse> handleGeneralException(ClientNameExists e) {
    //        ErrorResponse errorResponse = new ErrorResponse("Client name already exists", "GENERAL_ERROR");
    //        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    //
    //    }
}