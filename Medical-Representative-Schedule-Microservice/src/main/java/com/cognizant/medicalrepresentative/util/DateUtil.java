package com.cognizant.medicalrepresentative.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognizant.medicalrepresentative.controller.MedicalRepresentativeController;
/**
 * This is the DateUtil class to fetch the schedule start date in the given
 * format.
 */
public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(MedicalRepresentativeController.class);

	public static LocalDate getDate(String scheduleStartDate) throws Exception {
		logger.info("start");
		LocalDate localDate = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
			localDate = LocalDate.parse(scheduleStartDate, formatter);
		} catch (Exception e) {
		}
		logger.info("end");
		return localDate;
	}
}