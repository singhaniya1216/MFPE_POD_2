package com.cognizant.medicalrepresentative.exception;

/**
 * This is a user defined exception class to handle the exception when the given
 * date is invalid.
 */
public class InvalidDateException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDateException(String message) {
		super(message);
	}
}