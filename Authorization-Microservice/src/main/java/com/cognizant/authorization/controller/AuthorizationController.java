package com.cognizant.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authorization.model.JwtResponse;
import com.cognizant.authorization.model.UserLoginCredential;
import com.cognizant.authorization.model.UserToken;
import com.cognizant.authorization.repository.UserDetailRepository;
import com.cognizant.authorization.service.UserService;
import com.cognizant.authorization.util.JwtUtil;

@RestController
public class AuthorizationController {

	private UserService userService;
	private UserDetailRepository repository;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;

	@Autowired
	public AuthorizationController(UserService userService, UserDetailRepository repository,
			AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.userService = userService;
		this.repository = repository;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	private void authenticate(String userid, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));
		} catch (DisabledException e) {
			throw new Exception("USER DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserToken> login(@RequestBody UserLoginCredential userlogincredentials) throws Exception {
		authenticate(userlogincredentials.getUserId(), userlogincredentials.getPassword());
		final UserDetails userdetails = userService.loadUserByUsername(userlogincredentials.getUserId());
		return ResponseEntity.ok(new UserToken(userlogincredentials.getUserId(), jwtUtil.generateToken(userdetails)));
	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ResponseEntity<JwtResponse> getValidity(@RequestHeader("Authorization") final String token) {
		JwtResponse res = new JwtResponse();
		if (token == null) {
			res.setValid(false);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
		//	String token1 = token.substring(7);
			String token1 = new String(token);
			if (jwtUtil.validateToken(token1)) {
				res.setUserId(jwtUtil.extractUsername(token1));
				res.setValid(true);
				res.setUserName(repository.findById(res.getUserId()).orElse(null).getUserName());
			} else {
				res.setValid(false);
				return new ResponseEntity<>(res, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
