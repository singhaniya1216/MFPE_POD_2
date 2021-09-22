package com.cognizant.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity(name = "user_credentials")
public class UserDetail {

	@Id
	@Column(name = "user_id", length = 20)
	private String userId;

	@Column(name = "password", length = 20)
	private String password;

	@Column(name = "user_name", length = 20)
	private String userName;

}