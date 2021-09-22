package com.cognizant.medicinestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedicineStockMicroserviceApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(MedicineStockMicroserviceApplication.class, args);
	}
}
