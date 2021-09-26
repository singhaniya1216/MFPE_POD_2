package com.cognizant.medicalrepresentative.model;

/**
 * This is model class which is used as a response after the token is validated
 * successfully and it is having fields like userId, userName and isValid to
 * display whether the user is valid or not.
 */
public class JwtResponse {

	private String userId;
	private String userName;
	private boolean isValid;

	public JwtResponse() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
