package com.cognizant.medicinestock.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.cognizant.medicinestock.model.ErrorResponse;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> allExceptions(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setLocalDateTime(LocalDateTime.now());
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setReason("BAD REQUEST");
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
	}
}