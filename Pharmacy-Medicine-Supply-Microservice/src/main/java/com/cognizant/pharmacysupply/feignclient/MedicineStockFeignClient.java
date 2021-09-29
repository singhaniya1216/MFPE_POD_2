package com.cognizant.pharmacysupply.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.pharmacysupply.model.MedicineStock;

/**
 * This is a Feign Client to interact with Medicine-Stock microservice running
 * at port 8080.
 */

@FeignClient(url = "http://localhost:8080", name = "medicine-stock-service")
public interface MedicineStockFeignClient {

	@PostMapping("/getStockCount/{medicine}")
	public MedicineStock getStockCountForMedicine(@PathVariable("medicine") String medicine);

	@PostMapping("/updateStock/{medicine}/{count}")
	public Boolean updateNumberOfTabletsInStockByName(@PathVariable("medicine") String medicine,
			@PathVariable("count") int count);

}