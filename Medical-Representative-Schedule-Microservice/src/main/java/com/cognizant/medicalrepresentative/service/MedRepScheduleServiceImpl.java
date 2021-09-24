package com.cognizant.medicalrepresentative.service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicalrepresentative.exception.TokenValidationFailedException;
import com.cognizant.medicalrepresentative.feignclient.AuthenticationFeignClient;
import com.cognizant.medicalrepresentative.feignclient.MedicineStockFeignClient;
import com.cognizant.medicalrepresentative.model.Doctor;
import com.cognizant.medicalrepresentative.model.JwtResponse;
import com.cognizant.medicalrepresentative.model.MedicalRepresentative;
import com.cognizant.medicalrepresentative.model.RepSchedule;
import com.cognizant.medicalrepresentative.repository.MedicalRepresentativeRepository;
import com.cognizant.medicalrepresentative.util.CsvParseUtil;

/**
 * This is MedRepScheduleServiceImpl class which implements the
 * MedRepScheduleService interface.
 */
@Service
public class MedRepScheduleServiceImpl implements MedRepScheduleService {

	private MedicineStockFeignClient medicineStockClient;

	private AuthenticationFeignClient authFeignClient;

	private MedicalRepresentativeRepository repository;

	private Logger logger = LoggerFactory.getLogger(MedRepScheduleServiceImpl.class);

	@Autowired
	public MedRepScheduleServiceImpl(MedicalRepresentativeRepository repository,
			MedicineStockFeignClient medicineStockClient, AuthenticationFeignClient authFeignClient) {
		this.repository = repository;
		this.medicineStockClient = medicineStockClient;
		this.authFeignClient = authFeignClient;
	}

	@Override
	public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
			throws TokenValidationFailedException, IOException {
		logger.info("start");
		if (!isValidSession(token)) {
			return null;
		}
		List<RepSchedule> repSchedules = new ArrayList<>();
		List<Doctor> doctors = CsvParseUtil.parseDoctors();
		List<MedicalRepresentative> medicalRepresentatives = repository.findAll();

		LocalDate localDate = scheduleStartDate;

		LocalTime now = LocalTime.now();
		LocalTime one = LocalTime.of(13, 0);

		LocalDate today = LocalDate.now();

		if (scheduleStartDate.isBefore(today)) {
			return repSchedules;
		}
		if (scheduleStartDate.equals(today)) {
			if (now.isAfter(one)) {
				localDate = localDate.plusDays(1);
			}
		}
		for (int i = 0; i < doctors.size(); i++) {
			if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				localDate = localDate.plusDays(1);
			}
			Doctor doctor = doctors.get(i);
			MedicalRepresentative medicalRepresentative = medicalRepresentatives.get(i % medicalRepresentatives.size());

			RepSchedule repSchedule = new RepSchedule();
			repSchedule.setId(i + 1);
			repSchedule.setRepName(medicalRepresentative.getName());
			repSchedule.setDoctorName(doctor.getName());
			repSchedule.setDoctorContactNumber(doctor.getContactNumber());
			repSchedule.setMeetingDate(localDate);
			repSchedule.setMeetingSlot(doctor.getTime());
			repSchedule.setTreatingAilment(doctor.getTreatingAilment());

			List<String> medicinesByTreatingAilment = medicineStockClient
					.getMedicineByTreatingAilment(doctor.getTreatingAilment());

			repSchedule.setMedicines(medicinesByTreatingAilment);
			repSchedules.add(repSchedule);
			localDate = localDate.plusDays(1);
		}
		logger.info("end");
		return repSchedules;
	}

	public Boolean isValidSession(String token) throws TokenValidationFailedException {

		JwtResponse response = authFeignClient.verifyToken(token);

		if (!response.isValid()) {
			throw new TokenValidationFailedException("Invalid Token");
		}
		logger.info("end");
		return true;
	}

}
