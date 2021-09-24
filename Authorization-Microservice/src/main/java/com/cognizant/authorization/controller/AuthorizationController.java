package com.cognizant.authorization.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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

/**
 * This is the controller class for generating and validating JWT token
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthorizationController {

	private UserService userService;
	private UserDetailRepository repository;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	
	private static Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

	@Autowired
	public AuthorizationController(UserService userService, UserDetailRepository repository,
			AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.userService = userService;
		this.repository = repository;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * This method takes userid and password as parameters and authenticates the
	 * user by verifying the credentials entered by the user and also throws an
	 * exception if the credentials are wrong.
	 */
	private void authenticate(String userid, String password) throws Exception {
		try {
			logger.info("start");
			logger.info("end");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userid, password));

		} catch (DisabledException e) {
			throw new Exception("USER DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	/**
	 * This method calls the authenticate method to verify the credentials, if the
	 * credentials are correct, JWT token is generated and if the credentials are
	 * wrong an exception is thrown.
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserToken> login(@RequestBody UserLoginCredential userlogincredentials) throws Exception {
		logger.info("start");
		authenticate(userlogincredentials.getUserId(), userlogincredentials.getPassword());
		final UserDetails userdetails = userService.loadUserByUsername(userlogincredentials.getUserId());
		logger.info("end");
		return ResponseEntity.ok(new UserToken(userlogincredentials.getUserId(), jwtUtil.generateToken(userdetails)));
	}

	/**
	 * Once the token is generated this method is used to validate the token for
	 * every furthur request.It uses jwUtil class for that.
	 */
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ResponseEntity<JwtResponse> getValidity(@RequestHeader("Authorization") final String token) {
		logger.info("start");
		logger.info(token);
		JwtResponse res = new JwtResponse();
		if (token == null) {
			res.setValid(false);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			String token1 = token.substring(7);
			// String token1 = new String(token);
			if (jwtUtil.validateToken(token1)) {
				res.setUserId(jwtUtil.extractUsername(token1));
				res.setValid(true);
				res.setUserName(repository.findById(res.getUserId()).orElse(null).getUserName());
			} else {
				res.setValid(false);
				return new ResponseEntity<>(res, HttpStatus.OK);
			}
		}
		logger.info("end");
		logger.info(res.toString());
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}