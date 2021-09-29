package com.cognizant.pharmacysupply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * This is the application class of PharmacyMedicineSupply microservice.
 */
@SpringBootApplication
@EnableFeignClients
public class PharmacyMedicineSupplyMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyMedicineSupplyMicroserviceApplication.class, args);
	}

}
