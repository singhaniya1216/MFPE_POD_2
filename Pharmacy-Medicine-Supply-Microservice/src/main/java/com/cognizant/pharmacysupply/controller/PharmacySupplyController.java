package com.cognizant.pharmacysupply.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cognizant.pharmacysupply.service.PharmacySupplyService;

@RestController
public class PharmacySupplyController {

	private PharmacySupplyService pharmacyService;
	private AuthenticationFeignClient authFeignClient;
	private MedicalReprestativeFeignClient medicalRepClient;

	@Autowired
	public PharmacySupplyController(MedicalReprestativeFeignClient medicalRepClient,
			PharmacySupplyService pharmacyService, AuthenticationFeignClient authFeignClient) {
		this.medicalRepClient = medicalRepClient;
		this.pharmacyService = pharmacyService;
		this.authFeignClient = authFeignClient;
	}

	@PostMapping("/PharmacySupply")
	public ResponseEntity<List<MedicineSupply>> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@Valid @RequestBody List<MedicineDemand> medicineDemandList)
			throws MedicineNotFoundException, TokenValidationFailedException {
		List<MedicineSupply> pharmacySupply = null;
		if (isValidSession(token)) {
			pharmacySupply = pharmacyService.getPharmacySupplyCount(medicineDemandList);
			if (pharmacySupply == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			} else {
				return ResponseEntity.ok(pharmacySupply);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}

	@GetMapping("/getMedicineSupply")
	public ResponseEntity<List<MedicineSupply>> getMedicineSupply(@RequestHeader("Authorization") String token)
			throws TokenValidationFailedException {
		List<MedicineSupply> medicineSupply = null;
		if (isValidSession(token)) {
			medicineSupply = pharmacyService.getMedicineSupply();
			return ResponseEntity.ok(medicineSupply);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}

	@GetMapping("/getMedicineDemand")
	public ResponseEntity<List<MedicineDemand>> getMedicineDemand(@RequestHeader(name = "Authorization") String token)
			throws TokenValidationFailedException {
		List<MedicineDemand> medicineDemand = null;
		if (isValidSession(token)) {
			medicineDemand = pharmacyService.getMedicineDemand();
			return ResponseEntity.ok(medicineDemand);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}

	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public List<RepSchedule> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate){
		return medicalRepClient.getRepSchedule(token, scheduleStartDate);
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		return true;
	}

}