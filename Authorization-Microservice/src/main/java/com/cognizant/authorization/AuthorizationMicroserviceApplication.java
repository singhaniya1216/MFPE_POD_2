package com.cognizant.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cognizant.authorization.model.UserDetail;
import com.cognizant.authorization.repository.UserDetailRepository;

/**
 * This is the application class of Authorization Microservice.
 */
@SpringBootApplication
@EnableFeignClients
public class AuthorizationMicroserviceApplication implements CommandLineRunner {

	private UserDetailRepository repo;
	
	@Autowired
	public AuthorizationMicroserviceApplication(UserDetailRepository repo) {
		this.repo = repo;
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
		repo.save(new UserDetail("aman","aman","Singhaniya Aman"));
	}
}
