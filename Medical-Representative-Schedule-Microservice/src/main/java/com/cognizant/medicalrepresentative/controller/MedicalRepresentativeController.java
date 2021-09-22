package com.cognizant.medicalrepresentative.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicalrepresentative.exception.InvalidDateException;
import com.cognizant.medicalrepresentative.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentative.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentative.feignclient.MedicineStockFeignClient;
import com.cognizant.medicalrepresentative.model.Doctor;
import com.cognizant.medicalrepresentative.model.JwtResponse;
import com.cognizant.medicalrepresentative.model.MedicalRepresentative;
import com.cognizant.medicalrepresentative.model.RepSchedule;
import com.cognizant.medicalrepresentative.service.MedRepScheduleService;
import com.cognizant.medicalrepresentative.service.MedRepService;
import com.cognizant.medicalrepresentative.util.CsvParseUtil;
import com.cognizant.medicalrepresentative.util.DateUtil;

@RestController
public class MedicalRepresentativeController {

	private MedRepScheduleService scheduleService;
	private MedRepService medicalRepresentativeService;
	private AuthenticationFeignClient authFeignClient;
	private MedicineStockFeignClient medicineStockClient;

	@Autowired
	public MedicalRepresentativeController(MedRepScheduleService scheduleService,
			MedRepService medicalRepresentativeService, AuthenticationFeignClient authFeignClient,
			MedicineStockFeignClient medicineStockClient) {
		this.scheduleService = scheduleService;
		this.medicalRepresentativeService = medicalRepresentativeService;
		this.authFeignClient = authFeignClient;
		this.medicineStockClient = medicineStockClient;
	}

	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public ResponseEntity<List<RepSchedule>> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate) throws Exception {

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

		return ResponseEntity.ok(repSchedule);

	}

	@GetMapping
	public ResponseEntity<List<String>> getMedicinesByTreatingAilment(
			@RequestHeader(name = "Authorization") String token) {
		return ResponseEntity.ok(medicineStockClient.getMedicineByTreatingAilment("General"));
	}

	@GetMapping("/medicalRepresentatives")
	public List<MedicalRepresentative> getMedicalRepresentatives(
			@RequestHeader(name = "Authorization") final String token) throws TokenValidationFailedException {
		List<MedicalRepresentative> medicalRepresentatives = medicalRepresentativeService
				.getMedicalRepresentatives(token);
		return medicalRepresentatives;
	}

	@GetMapping("/doctors")
	public List<Doctor> getDoctors() throws IOException {
		List<Doctor> doctors = CsvParseUtil.parseDoctors();
		return doctors;
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		return true;
	}

}
