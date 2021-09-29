package com.cognizant.medicalrepresentative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.medicalrepresentative.model.MedicalRepresentative;
import com.cognizant.medicalrepresentative.repository.MedicalRepresentativeRepository;

/**
 * This is the application class of Medicine-Representative-Schedule
 * Microservice
 */
@SpringBootApplication
@EnableFeignClients
public class MedicalRepresentativeScheduleMicroserviceApplication implements CommandLineRunner {

	@Autowired
	private MedicalRepresentativeRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(MedicalRepresentativeScheduleMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repo.save(new MedicalRepresentative("Singhaniya Aman"));
		repo.save(new MedicalRepresentative("Deepak Parmar"));
		repo.save(new MedicalRepresentative("Sanaka Pradeep"));
		repo.save(new MedicalRepresentative("Karnataka Shushma"));
		// TODO Auto-generated method stub
		
	}

}
