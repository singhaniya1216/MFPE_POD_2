package com.cognizant.authorization.exception;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4719079937237138117L;

	public UserNotFoundException(String message) {
		super(message);
	}

}