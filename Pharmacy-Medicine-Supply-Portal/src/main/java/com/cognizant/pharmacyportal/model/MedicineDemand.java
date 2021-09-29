package com.cognizant.pharmacyportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicineDemand {
	
	private String medicineName;	
	private int demandCount;
}
