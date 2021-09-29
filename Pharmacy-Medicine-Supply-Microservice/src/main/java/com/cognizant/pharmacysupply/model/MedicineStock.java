package com.cognizant.pharmacysupply.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * This is the entity class for Medicine Stock having fields id, name,
 * chemicalComposition, targetAilment, dateofExpiry, numberOfTabletsInStock,
 * pharmacyName.
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicineStock {

	private int id;
	private String name;
	private String chemicalComposition;
	private String targetAilment;
	private LocalDate dateOfExpiry;
	private int numberOfTabletsInStock;
	private String pharmacyName;

}
