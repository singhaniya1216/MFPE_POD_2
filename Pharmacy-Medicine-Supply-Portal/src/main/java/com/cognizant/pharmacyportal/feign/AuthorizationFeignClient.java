package com.cognizant.pharmacyportal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.cognizant.pharmacyportal.model.JwtResponse;
import com.cognizant.pharmacyportal.model.UserLoginCredential;
import com.cognizant.pharmacyportal.model.UserToken;

@FeignClient(name = "authorization-service", url = "http://localhost:8084")
public interface AuthorizationFeignClient {

	@PostMapping("/login")
	public UserToken login(@RequestBody UserLoginCredential userlogincredentials);
	
	@GetMapping("/validate")
	public JwtResponse getValidity(@RequestHeader(value = "Authorization", required = true) String token);
}
