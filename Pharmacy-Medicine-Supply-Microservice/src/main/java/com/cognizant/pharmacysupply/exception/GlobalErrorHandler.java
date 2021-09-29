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

/**
 * This class handles all the errors. All the methods in the class corresponds
 * to an Exception and returns an object of ErrorResponse class with fields like
 * time stamp, message etc.
 */
@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@Autowired
	ErrorResponse errorResponse;

	/**
	 * This method handles all the errors and returns a message with reason and
	 * status.
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllErrors(Exception ex) {
		logger.info("start");
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setReason("BAD_REQUEST");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setTimestamp(LocalDateTime.now());
		logger.info("end");
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	/**
	 * This method handles the exception caused when the medicine is not found in
	 * the stock.
	 * 
	 * @param e
	 * @return
	 */

	@ExceptionHandler(MedicineNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleFeignStatusException(MedicineNotFoundException e) {
		logger.info("start");
		ErrorResponse customErrorResponse = new ErrorResponse();
		customErrorResponse.setStatus(HttpStatus.FORBIDDEN);
		customErrorResponse.setMessage("The medicine is not present in the stock");
		customErrorResponse.setReason("You might have entered wrong medicine name.");
		customErrorResponse.setTimestamp(LocalDateTime.now());
		logger.info("end");
		return new ResponseEntity<ErrorResponse>(customErrorResponse, HttpStatus.FORBIDDEN);
	}

	/**
	 * This method handles the exception caused when token validation fails.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(TokenValidationFailedException.class)
	public ResponseEntity<ErrorResponse> handleTokenValidationFailedException(TokenValidationFailedException e) {
		logger.info("start");
		errorResponse.setMessage("Your token is invalid");
		errorResponse.setReason("Your token might have been expired or you have entered wrong token");
		errorResponse.setStatus(HttpStatus.FORBIDDEN);
		errorResponse.setTimestamp(LocalDateTime.now());
		logger.info("end");
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.FORBIDDEN);
	}
}