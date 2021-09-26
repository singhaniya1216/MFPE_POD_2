package com.cognizant.pharmacysupply.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import com.cognizant.pharmacysupply.model.RepSchedule;

/**
 * This is a Feign Client to interact with Medical-Reresentative
 * microservice running at port 8081.
 */
@FeignClient(name = "medical-representative-service", url = "http://localhost:8081")
public interface MedicalReprestativeFeignClient {

	@GetMapping("/RepSchedule/{scheduleStartDate}")
	public List<RepSchedule> getRepSchedule(@RequestHeader(name = "Authorization") final String token,
			@PathVariable("scheduleStartDate") final String scheduleStartDate);
}
