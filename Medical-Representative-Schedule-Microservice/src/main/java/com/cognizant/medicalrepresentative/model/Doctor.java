package com.cognizant.medicalrepresentative.model;

/**
 * This is the entity class of doctors having fields id,name,contactNumber,
 * treatingAilment, time.
 */
public class Doctor {

	private int id;
	private String name;
	private String contactNumber;
	private String treatingAilment;
	private String time;

	public Doctor() {
	}

	public Doctor(int id, String name, String contactNumber, String treatingAilment, String time) {
		this.id = id;
		this.name = name;
		this.contactNumber = contactNumber;
		this.treatingAilment = treatingAilment;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getTreatingAilment() {
		return treatingAilment;
	}

	public void setTreatingAilment(String treatingAilment) {
		this.treatingAilment = treatingAilment;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}