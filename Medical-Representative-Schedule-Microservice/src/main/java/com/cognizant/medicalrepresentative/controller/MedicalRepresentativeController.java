package com.cognizant.medicalrepresentative.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicalrepresentative.exception.InvalidDateException;
import com.cognizant.medicalrepresentative.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentative.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentative.model.JwtResponse;
import com.cognizant.medicalrepresentative.model.RepSchedule;
import com.cognizant.medicalrepresentative.model.UserLoginCredential;
import com.cognizant.medicalrepresentative.model.UserToken;
import com.cognizant.medicalrepresentative.service.MedRepScheduleService;
import com.cognizant.medicalrepresentative.util.DateUtil;

/**
 * This is the controller class of Medicine-Representative-Schedule
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class MedicalRepresentativeController {

	private MedRepScheduleService scheduleService;
	private AuthenticationFeignClient authFeignClient;
	private static Logger logger = LoggerFactory.getLogger(MedicalRepresentativeController.class);

	@Autowired
	public MedicalRepresentativeController(MedRepScheduleService scheduleService,
		 AuthenticationFeignClient authFeignClient) {
		this.scheduleService = scheduleService;
		this.authFeignClient = authFeignClient;
	}
	
	
	@PostMapping("/login")
	public UserToken loginPortal(@RequestBody UserLoginCredential userlogincredentials) {
		return authFeignClient.login(userlogincredentials);
	}

	/**
	 * This method first validates the token and checks whether the start date is
	 * null or not then it returns the RepSchedule if the token is validated
	 * successfully and the start date is not null, else it throws an exception.
	 * 
	 * @param token
	 * @param scheduleStartDate
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public ResponseEntity<List<RepSchedule>> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate) throws Exception {
		logger.info("start");
		List<RepSchedule> repSchedule = null;
		LocalDate localDate = DateUtil.getDate(scheduleStartDate);
		if (!isValidSession(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		if (localDate == null) {
			throw new InvalidDateException("Invalid date");
		}

		repSchedule = scheduleService.getRepSchedule(token, localDate);

		if (repSchedule.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		logger.info("end");
		return ResponseEntity.ok(repSchedule);

	}

	/**
	 * This method validates the token and throws an exception if the token is
	 * invalid.
	 * 
	 * @param token
	 * @return
	 * @throws TokenValidationFailedException
	 */
	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		logger.info("start");
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		logger.info("end");
		return true;
	}

}
