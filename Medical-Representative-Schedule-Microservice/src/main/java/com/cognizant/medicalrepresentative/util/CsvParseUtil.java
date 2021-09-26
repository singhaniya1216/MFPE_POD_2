package com.cognizant.medicalrepresentative.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognizant.medicalrepresentative.model.Doctor;
import com.cognizant.medicalrepresentative.service.MedRepServiceImpl;

/**
 * This class fetches the details of the doctors from the csv file.
 */
public class CsvParseUtil {
	private static Logger logger = LoggerFactory.getLogger(MedRepServiceImpl.class);

	public static List<Doctor> parseDoctors() throws IOException {
		logger.info("start");
		final List<Doctor> doctors = new ArrayList<>();
		ClassLoader classLoader = CsvParseUtil.class.getClassLoader();
		InputStream in = classLoader.getResourceAsStream("doctor.csv");
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			line = bufferedReader.readLine();
			while (line != null) {
				String[] entry = line.split(",");
				String ids = entry[0].trim();
				ids = ids.replaceAll("[^0-9]+", "");
				int id = Integer.parseInt(ids);
				String name = entry[1].trim();
				String contactNumber = entry[2].trim();
				String treatingAilment = entry[3].trim();
				String time = entry[4].trim();
				Doctor doctor = new Doctor(id, name, contactNumber, treatingAilment, time);
				doctors.add(doctor);
				line = bufferedReader.readLine();
			}
		} catch (IOException e) {
		}
		logger.info("end");
		return doctors;
	}
}