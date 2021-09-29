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
import com.cognizant.pharmacyportal.feign.AuthorizationFeignClient;
import com.cognizant.pharmacyportal.feign.MedicineStockFeignClient;
import com.cognizant.pharmacyportal.feign.PharmacySupplyFeignClient;
import com.cognizant.pharmacyportal.model.JwtResponse;
import com.cognizant.pharmacyportal.model.MedicineDemand;
import com.cognizant.pharmacyportal.model.MedicineStock;
import com.cognizant.pharmacyportal.model.MedicineSupply;
import com.cognizant.pharmacyportal.model.RepSchedule;
import com.cognizant.pharmacyportal.model.UserLoginCredential;
import com.cognizant.pharmacyportal.model.UserToken;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/portal")
@Slf4j
public class PortalController {
	
	private AuthorizationFeignClient authClient;
	private PharmacySupplyFeignClient supplyClient;
	private MedicineStockFeignClient stockClient;
	
	@Autowired
	public PortalController(AuthorizationFeignClient authClient, PharmacySupplyFeignClient supplyClient,
			MedicineStockFeignClient stockClient) {
		this.authClient = authClient;
		this.supplyClient = supplyClient;
		this.stockClient = stockClient;
	}

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
	
	
	@GetMapping("/getMedicineDemand")
	public List<MedicineDemand> getMedicineDemand(@RequestHeader(name = "Authorization") String token){
		return supplyClient.getMedicineDemand(token);
	}

	@GetMapping("/getMedicineSupply")
	public List<MedicineSupply> getMedicineSupply(@RequestHeader("Authorization") String token){
		return supplyClient.getMedicineSupply(token);
	}

	@PostMapping("/PharmacySupply")
	public List<MedicineSupply> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@RequestBody List<MedicineDemand> medicineDemandList){
		return supplyClient.getPharmacySupply(token, medicineDemandList);
	}
	
	
	@GetMapping("/MedicineStockInformation")
	public List<MedicineStock> medicineStockInformation(){
		return stockClient.medicineStockInformation();
	}
	
	@GetMapping("/check")
	public Boolean isValidorNot(@RequestHeader(name = "Authorization") String token){
		JwtResponse response = authClient.getValidity(token);
		if (!response.isValid()) {
			return false;
		}
		return true;
	}

}
