package com.cognizant.pharmacysupply.controller;

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

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.feignclient.AuthenticationFeignClient;
import com.cognizant.pharmacysupply.feignclient.MedicalReprestativeFeignClient;
import com.cognizant.pharmacysupply.model.JwtResponse;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineSupply;
import com.cognizant.pharmacysupply.model.RepSchedule;
import com.cognizant.pharmacysupply.model.UserLoginCredential;
import com.cognizant.pharmacysupply.model.UserToken;
import com.cognizant.pharmacysupply.service.PharmacySupplyService;
import com.google.gson.Gson;

/**
 * This is the controller class of Pharmacy-Medicine-Supply-Microservice.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class PharmacySupplyController {

	private PharmacySupplyService pharmacyService;
	private AuthenticationFeignClient authFeignClient;
	private MedicalReprestativeFeignClient medicalRepClient;
	private static Logger logger = LoggerFactory.getLogger(PharmacySupplyController.class);

	@Autowired
	public PharmacySupplyController(MedicalReprestativeFeignClient medicalRepClient,
			PharmacySupplyService pharmacyService, AuthenticationFeignClient authFeignClient) {
		this.medicalRepClient = medicalRepClient;
		this.pharmacyService = pharmacyService;
		this.authFeignClient = authFeignClient;
	}
	
	
	@PostMapping("/login")
	public UserToken loginPortal(@RequestBody UserLoginCredential userlogincredentials) {
		return authFeignClient.login(userlogincredentials);
	}

	/**
	 * This method first validates the token,if the validation is successful, it
	 * returns the medicine demand list and also gives an error message if token
	 * validation fails.
	 * 
	 * @param token
	 * @param medicineDemandList
	 * @return
	 * @throws MedicineNotFoundException
	 * @throws TokenValidationFailedException
	 */
	@PostMapping("/PharmacySupply")
	public ResponseEntity<List<MedicineSupply>> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@RequestBody List<MedicineDemand> medicineDemandList)
			throws MedicineNotFoundException, TokenValidationFailedException {
		logger.info("start");
		logger.info("List"+medicineDemandList);
		List<MedicineSupply> pharmacySupply = null;
		if (!isValidSession(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		pharmacySupply = pharmacyService.getPharmacySupplyCount(medicineDemandList);
		logger.info("LIST"+pharmacySupply);
		if (pharmacySupply == null) {
			logger.info("end");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			logger.info("end");
			return ResponseEntity.ok(pharmacySupply);
		}
	}

	/**
	 * This method returns the medicine supply list after validating the token.
	 * 
	 * @param token
	 * @return
	 * @throws TokenValidationFailedException
	 */

	@GetMapping("/getMedicineSupply")
	public ResponseEntity<List<MedicineSupply>> getMedicineSupply(@RequestHeader("Authorization") String token)
			throws TokenValidationFailedException {
		logger.info("start");
		List<MedicineSupply> medicineSupply = null;
		if (!isValidSession(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		medicineSupply = pharmacyService.getMedicineSupply();
		logger.info("end");
		return ResponseEntity.ok(medicineSupply);
	}

	/**
	 * This method returns the medicine demand after validating the token.
	 * 
	 * @param token
	 * @return
	 * @throws TokenValidationFailedException
	 */

	@GetMapping("/getMedicineDemand")
	public ResponseEntity<List<MedicineDemand>> getMedicineDemand(@RequestHeader(name = "Authorization") String token)
			throws TokenValidationFailedException {
		logger.info("start");
		List<MedicineDemand> medicineDemand = null;
		if (!isValidSession(token)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		medicineDemand = pharmacyService.getMedicineDemand();
		logger.info("end");
		return ResponseEntity.ok(medicineDemand);
	}

	/**
	 * This method takes start date as input and returns the schedule of the
	 * representatives starting from then given date except Sunday.
	 * 
	 * @param token
	 * @param scheduleStartDate
	 * @return
	 */
	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public List<RepSchedule> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate) {
		logger.info("start");
		logger.info("end");
		return medicalRepClient.getRepSchedule(token, scheduleStartDate);
	}

	/**
	 * This method validates the token and throws an exception if token validation
	 * fails.
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