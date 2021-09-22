package com.cognizant.medicalrepresentative.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentative.dao.MedicalRepresentativeRepository;
import com.cognizant.medicalrepresentative.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentative.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentative.model.JwtResponse;
import com.cognizant.medicalrepresentative.model.MedicalRepresentative;

@Service
public class MedRepServiceImpl implements MedRepService {

	private AuthenticationFeignClient authFeignClient;

	private MedicalRepresentativeRepository repository;

	@Autowired
	public MedRepServiceImpl(AuthenticationFeignClient authFeignClient, MedicalRepresentativeRepository repository) {
		this.authFeignClient = authFeignClient;
		this.repository = repository;
	}

	@Override
	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException {
		if (!isValidSession(token)) {
			return null;
		}
		return repository.findAll();
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		return true;
	}

}