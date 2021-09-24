package com.cognizant.authorization.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.authorization.model.ErrorResponse;

/**
 * This class handles all the errors. All the methods in the class 
 * corresponds to an Exception and  returns an object of ErrorResponse class with
 * fields like time stamp, message etc.
 * 
 */

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method handles all the errors related to medicine stock
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllMedicineStockErrors(Exception e) {
		logger.info("start");	
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(e.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setReason("BAD REQUEST");	
		logger.info("end");	
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}

	/**
	 * This method handles the Internal Authentication Exception
	 */
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity<ErrorResponse> handleInternalAuthenticationServiceException(
			InternalAuthenticationServiceException ex) {
		logger.info("start");		
		ErrorResponse response = new ErrorResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.FORBIDDEN);
		response.setReason("Entered Credentials are wrong.");
		logger.info("start");	
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
	}

}
