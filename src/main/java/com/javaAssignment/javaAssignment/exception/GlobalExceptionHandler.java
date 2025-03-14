package com.javaAssignment.javaAssignment.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	  @ExceptionHandler(CustomerNotFoundException.class)
	    public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(InvalidAmountException.class)
	    public ResponseEntity<Object> handleInvalidAmount(InvalidAmountException ex) {
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handleGenericException(Exception ex) {
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), "An unexpected error occurred: " + ex.getMessage());
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

}
