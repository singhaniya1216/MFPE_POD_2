package com.cognizant.authorization.exception;

/**
 * This is an User defined exception class to throw an exception when a user is
 * not found in the database.
 */
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4719079937237138117L;

	public UserNotFoundException(String message) {
		super(message);
	}
}