package com.cognizant.medicalrepresentative.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.medicalrepresentative.model.Doctor;

public class CsvParseUtil {

	public static List<Doctor> parseDoctors() throws IOException {
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
		return doctors;
	}
}