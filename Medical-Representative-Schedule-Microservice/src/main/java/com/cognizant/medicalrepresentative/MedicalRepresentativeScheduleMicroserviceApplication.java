package com.cognizant.medicalrepresentative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MedicalRepresentativeScheduleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRepresentativeScheduleMicroserviceApplication.class, args);
	}

}
