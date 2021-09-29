package com.cognizant.pharmacyportal.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.pharmacyportal.model.MedicineDemand;
import com.cognizant.pharmacyportal.model.MedicineSupply;
import com.cognizant.pharmacyportal.model.RepSchedule;

@FeignClient(name = "pharmacy-medicine-supply-service", url = "http://localhost:8082")
public interface PharmacySupplyFeignClient {

	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public List<RepSchedule> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate);

	@GetMapping("/getMedicineDemand")
	public List<MedicineDemand> getMedicineDemand(@RequestHeader(name = "Authorization") String token);

	@GetMapping("/getMedicineSupply")
	public List<MedicineSupply> getMedicineSupply(@RequestHeader("Authorization") String token);

	@PostMapping("/PharmacySupply")
	public List<MedicineSupply> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@RequestBody List<MedicineDemand> medicineDemandList);
}
