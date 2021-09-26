package com.cognizant.pharmacyportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineSupply {

	private String pharmacyName;
	private String medicineName;
	private int supplyCount;
	
}
