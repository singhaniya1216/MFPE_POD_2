package com.cognizant.pharmacyportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDemand {
	
	private String medicineName;	
	private int demandCount;
}
