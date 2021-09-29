package com.cognizant.pharmacyportal.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.pharmacyportal.model.ErrorResponse;

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

}