package com.cognizant.medicalrepresentative.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.medicalrepresentative.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentative.model.MedicalRepresentative;

/**
 * This is MedRepService interface.
 */
@Component
public interface MedRepService {

	public List<MedicalRepresentative> getMedicalRepresentatives(String token) throws TokenValidationFailedException;

}