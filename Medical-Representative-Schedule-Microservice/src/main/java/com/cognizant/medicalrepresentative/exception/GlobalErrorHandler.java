package com.cognizant.medicalrepresentative.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.medicalrepresentative.model.ErrorResponse;

/**
 * This class handles all the errors. All the methods in the class corresponds
 * to an Exception and returns an object of ErrorResponse class with fields like
 * time stamp, message etc.
 */
@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	ErrorResponse errorResponse;

	@Autowired
	public GlobalErrorHandler(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	/**
	 * This method handles all the errors and displays a message with a reason and
	 * timestamp.
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
	 * This method handles the method argument mismatch exception.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgTypeMismatchException(MethodArgumentTypeMismatchException e) {
		logger.info("start");
		errorResponse.setMessage("Please enter the date in dd-MM-yyyy format");
		errorResponse.setReason("You need to provide date in dd-MM-yyyy format");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST);
		errorResponse.setTimestamp(LocalDateTime.now());
		logger.info("end");
		return ResponseEntity.badRequest().body(errorResponse);
	}

	/**
	 * This method throws an exception when the given date is not valid.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<ErrorResponse> handleDateNotFoundException(InvalidDateException e) {
		logger.info("start");
		errorResponse.setMessage("Please enter the date in dd-MM-yyyy format");
		errorResponse.setReason("You need to provide date in dd-MM-yyyy format");
		errorResponse.setStatus(HttpStatus.NOT_FOUND);
		errorResponse.setTimestamp(LocalDateTime.now());
		logger.info("end");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	/**
	 * This method throws an exception when the token validation fails.
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