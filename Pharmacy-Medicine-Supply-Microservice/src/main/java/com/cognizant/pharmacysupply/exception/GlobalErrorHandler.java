package com.cognizant.pharmacysupply.exception;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.pharmacysupply.model.ErrorResponse;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@Autowired
	ErrorResponse errorResponse;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllErrors(Exception ex) {
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setReason("BAD_REQUEST");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setTimestamp(LocalDateTime.now());
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(MedicineNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFeignStatusException(MedicineNotFoundException e) {
		ErrorResponse customErrorResponse = new ErrorResponse();
		customErrorResponse.setStatus(HttpStatus.FORBIDDEN);
		customErrorResponse.setMessage("The medicine is not present in the stock");
		customErrorResponse.setReason("You might have entered wrong medicine name.");
		customErrorResponse.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(customErrorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementExceptionException(NoSuchElementException e) {
		ErrorResponse customErrorResponse = new ErrorResponse();
		customErrorResponse.setStatus(HttpStatus.FORBIDDEN);
		customErrorResponse.setMessage("The medicine is not present in the stock.");
		customErrorResponse
				.setReason("You might have entered wrong medicine name or medicine not present in the stock");
		customErrorResponse.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(customErrorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(TokenValidationFailedException.class)
	public ResponseEntity<ErrorResponse> handleTokenValidationFailedException(TokenValidationFailedException e) {
		errorResponse.setMessage("Your token is invalid");
		errorResponse.setReason("Your token might have been expired or you have entered wrong token");
		errorResponse.setStatus(HttpStatus.FORBIDDEN);
		errorResponse.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
	}
}