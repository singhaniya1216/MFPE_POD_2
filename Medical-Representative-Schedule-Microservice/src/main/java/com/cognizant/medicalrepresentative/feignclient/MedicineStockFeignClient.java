package com.cognizant.medicalrepresentative.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This Feign Client is used to connect to medicine-stock service present at the
 * URL
 */
@FeignClient(url = "http://localhost:8080", name = "medicine-stock-service")
public interface MedicineStockFeignClient {

	@PostMapping("/byTreatingAilment/{treatingAilment}")
	public List<String> getMedicineByTreatingAilment(@PathVariable("treatingAilment") String treatingAilment);

}