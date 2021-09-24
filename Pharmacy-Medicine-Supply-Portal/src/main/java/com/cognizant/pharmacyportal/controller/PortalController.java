package com.cognizant.pharmacyportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pharmacyportal.exception.TokenValidationFailedException;
import com.cognizant.pharmacyportal.feign.AuthorizationFeignClient;
import com.cognizant.pharmacyportal.feign.PharmacySupplyFeignClient;
import com.cognizant.pharmacyportal.model.JwtResponse;
import com.cognizant.pharmacyportal.model.RepSchedule;
import com.cognizant.pharmacyportal.model.UserLoginCredential;
import com.cognizant.pharmacyportal.model.UserToken;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/portal")
@Slf4j
public class PortalController {
	
	@Autowired
	private AuthorizationFeignClient authClient;
	
	@Autowired
	private PharmacySupplyFeignClient supplyClient;
	
	@PostMapping("/login")
	public UserToken loginPortal(@RequestBody UserLoginCredential userlogincredentials) {
		return authClient.login(userlogincredentials);
	}
	
	@GetMapping("/validate")
	public JwtResponse validate(@RequestHeader(value = "Authorization", required = true) String token) {
		log.info(token);
		return authClient.getValidity(token);
	}
	
	@GetMapping("/RepSchedule/{repDate}")
	public List<RepSchedule> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("repDate") final String repDate){
		return supplyClient.getRepSchedule(token, repDate);
	}
	
	
	
	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		JwtResponse response = authClient.getValidity(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		return true;
	}

}
