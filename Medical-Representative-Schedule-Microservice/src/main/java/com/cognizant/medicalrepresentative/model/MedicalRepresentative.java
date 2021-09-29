package com.cognizant.medicalrepresentative.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is a model class which is used as a table using Spring Data
 * JPA's @Entity annotation. It contains fields like id,name and phoneNumber.
 */
@Entity
@Table(name = "medical_representatives")
public class MedicalRepresentative {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String name;


	public MedicalRepresentative(String name) {
		this.name = name;
	}

	public MedicalRepresentative() {
	}

	public String getName() {
		return name;
	}


}