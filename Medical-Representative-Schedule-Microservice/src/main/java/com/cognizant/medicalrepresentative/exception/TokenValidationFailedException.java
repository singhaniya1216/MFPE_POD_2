package com.cognizant.medicalrepresentative.exception;

/**
 * This class handles the exception thrown when the token is invalid.
 */
public class TokenValidationFailedException extends Exception {
	private static final long serialVersionUID = 1L;

	public TokenValidationFailedException() {
		super();
	}

	public TokenValidationFailedException(String message) {
		super(message);
	}
}