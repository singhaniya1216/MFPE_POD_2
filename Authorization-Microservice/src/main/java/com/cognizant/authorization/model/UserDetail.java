package com.cognizant.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

/**
 * This is a model class which is used as a table using Spring Data
 * JPA's @Entity annotation. It contains fields like userId,password and
 * userName.
 */
@Entity
@AllArgsConstructor
@Table(name = "user_credentials")
public class UserDetail {

	@Id
	@Column(name = "user_id", length = 20)
	private String userId;

	@Column(name = "password", length = 20)
	private String password;

	@Column(name = "user_name", length = 20)
	private String userName;

	public UserDetail() {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}