package com.cognizant.medicalrepresentative.model;

import lombok.NoArgsConstructor;

/**
 * This is a model class which is used as a response for the login method of
 * JwtAuthenticationController class. This class contains fields like userId and
 * the authentication Token generated in that method.
 *
 */
@NoArgsConstructor
public class UserToken {

	private String userId;
	private String authToken;

	public String getUserId() {
		return userId;
	}

	public String getAuthToken() {
		return authToken;
	}


}
