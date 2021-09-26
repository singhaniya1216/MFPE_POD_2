package com.cognizant.pharmacysupply.model;

import java.time.LocalDate;
import java.util.List;

/**
 * This is the entity class of RepSchedule having fields id,repName,
 * doctorName,meetingSlot,meetingDate,doctorcontactNumber,medicine,treatingAilment.
 */
public class RepSchedule {

	private int id;
	private String repName;
	private String doctorName;
	private String meetingSlot;
	private LocalDate meetingDate;
	private String doctorContactNumber;
	private List<String> medicines;
	private String treatingAilment;

	public RepSchedule() {
	}

	public RepSchedule(int id, String repName, String doctorName, String meetingSlot, LocalDate meetingDate,
			String doctorContactNumber, List<String> medicines, String treatingAilment) {
		this.id = id;
		this.repName = repName;
		this.doctorName = doctorName;
		this.meetingSlot = meetingSlot;
		this.meetingDate = meetingDate;
		this.doctorContactNumber = doctorContactNumber;
		this.medicines = medicines;
		this.treatingAilment = treatingAilment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getMeetingSlot() {
		return meetingSlot;
	}

	public void setMeetingSlot(String meetingSlot) {
		this.meetingSlot = meetingSlot;
	}

	public LocalDate getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getDoctorContactNumber() {
		return doctorContactNumber;
	}

	public void setDoctorContactNumber(String doctorContactNumber) {
		this.doctorContactNumber = doctorContactNumber;
	}

	public List<String> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<String> medicines) {
		this.medicines = medicines;
	}

	public String getTreatingAilment() {
		return treatingAilment;
	}

	public void setTreatingAilment(String treatingAilment) {
		this.treatingAilment = treatingAilment;
	}

}