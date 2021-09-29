package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pharmacysupply.model.JwtResponse;
import com.cognizant.pharmacysupply.model.UserLoginCredential;
import com.cognizant.pharmacysupply.model.UserToken;

/**
 * This is a Feign Client to interact with Authorization microservice running at
 * port 8084.
 */
@FeignClient(name = "authorization-service", url = "http://localhost:8084")
public interface AuthenticationFeignClient {

	@GetMapping(value = "/validate")
	public JwtResponse verifyToken(@RequestHeader(name = "Authorization", required = true) String token);
	
	@PostMapping("/login")
	public UserToken login(@RequestBody UserLoginCredential userlogincredentials);

}