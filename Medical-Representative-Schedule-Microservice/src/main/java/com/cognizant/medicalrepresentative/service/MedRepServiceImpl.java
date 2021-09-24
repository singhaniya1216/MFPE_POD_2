package com.cognizant.medicalrepresentative.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentative.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentative.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentative.model.JwtResponse;
import com.cognizant.medicalrepresentative.model.MedicalRepresentative;
import com.cognizant.medicalrepresentative.repository.MedicalRepresentativeRepository;

/**
 * This is MedRepServiceImpl class which implements MedRepService interface.
 */
@Service
public class MedRepServiceImpl implements MedRepService {

	private AuthenticationFeignClient authFeignClient;

	private MedicalRepresentativeRepository repository;
	private static Logger logger = LoggerFactory.getLogger(MedRepServiceImpl.class);

	@Autowired
	public MedRepServiceImpl(AuthenticationFeignClient authFeignClient, MedicalRepresentativeRepository repository) {
		this.authFeignClient = authFeignClient;
		this.repository = repository;
	}

	@Override
	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException {
		logger.info("start");
		if (!isValidSession(token)) {
			return null;
		}
		logger.info("end");
		return repository.findAll();
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {
		logger.info("start");
		JwtResponse response = authFeignClient.verifyToken(token);
		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		logger.info("end");
		return true;
	}

}