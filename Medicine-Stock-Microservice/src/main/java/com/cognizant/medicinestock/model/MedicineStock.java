package com.cognizant.medicinestock.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

/**
 * This is a model class which is used as a table using Spring Data
 * JPA's @Entity annotation.
 */
@Entity
@AllArgsConstructor
@Table(name = "medicine_stock")
public class MedicineStock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "medicine_name", unique = true)
	private String name;

	@Column(name = "chemical_composition")
	private String chemicalComposition;

	@Column(name = "target_ailment")
	private String targetAilment;

	@Column(name = "date_of_expiry")
	private LocalDate dateOfExpiry;

	@Column(name = "stock")
	private int numberOfTabletsInStock;

	@Column(name = "pharmacy_name")
	private String pharmacyName;

	public MedicineStock() {
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

	public String getChemicalComposition() {
		return chemicalComposition;
	}

	public void setChemicalComposition(String chemicalComposition) {
		this.chemicalComposition = chemicalComposition;
	}

	public String getTargetAilment() {
		return targetAilment;
	}

	public void setTargetAilment(String targetAilment) {
		this.targetAilment = targetAilment;
	}

	public LocalDate getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(LocalDate dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public int getNumberOfTabletsInStock() {
		return numberOfTabletsInStock;
	}

	public void setNumberOfTabletsInStock(int numberOfTabletsInStock) {
		this.numberOfTabletsInStock = numberOfTabletsInStock;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

}
