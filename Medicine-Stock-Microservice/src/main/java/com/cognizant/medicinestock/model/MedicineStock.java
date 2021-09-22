package com.cognizant.medicinestock.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medicine_stock")
public class MedicineStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
