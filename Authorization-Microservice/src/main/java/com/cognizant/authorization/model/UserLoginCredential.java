package com.cognizant.authorization.model;

/**
 * This is a model class which is passed as an argument to generate token in
 * login method of the JwtAuthenticationController class.This class contains
 * fields like userId and password.
 */
public class UserLoginCredential {

	private String userId;
	private String password;

	public UserLoginCredential() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
