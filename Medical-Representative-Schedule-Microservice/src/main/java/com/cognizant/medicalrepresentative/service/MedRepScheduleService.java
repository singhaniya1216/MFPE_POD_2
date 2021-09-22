package com.cognizant.medicalrepresentative.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.medicalrepresentative.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentative.model.RepSchedule;

@Component
public interface MedRepScheduleService {
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
			throws TokenValidationFailedException, IOException;

}