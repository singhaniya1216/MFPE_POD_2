package com.cognizant.medicalrepresentative.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.medicalrepresentative.model.JwtResponse;
import com.cognizant.medicalrepresentative.model.UserLoginCredential;
import com.cognizant.medicalrepresentative.model.UserToken;

/**
 * This Feign Client is used to connect to authorization-service present at the
 * URL
 */
@FeignClient(name = "authorization-service", url = "http://localhost:8084")
public interface AuthenticationFeignClient {

	@GetMapping(value = "/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);
	
	@PostMapping("/login")
	public UserToken login(@RequestBody UserLoginCredential userlogincredentials);

}