package com.cognizant.pharmacysupply.exception;

/**
 * This is a Token Validation exception class which handles the exception thrown
 * when the token validation fails.
 */
public class TokenValidationFailedException extends Exception {
	private static final long serialVersionUID = 1L;

	public TokenValidationFailedException(String message) {
		super(message);
	}
}