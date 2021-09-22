package com.cognizant.medicalrepresentative.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {

	private String userId;
	private String userName;
	private boolean isValid;
}