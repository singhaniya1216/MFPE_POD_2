package com.cognizant.pharmacyportal.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cognizant.pharmacyportal.model.MedicineStock;


@FeignClient(name = "medicine-stock-service", url = "http://localhost:8080")
public interface MedicineStockFeignClient {

	@GetMapping("/MedicineStockInformation")
	public List<MedicineStock> medicineStockInformation();
}
