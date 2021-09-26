package com.cognizant.pharmacyportal.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicineStock {

	private String name;
	private String chemicalComposition;
	private String targetAilment;
	private LocalDate dateOfExpiry;
	private int numberOfTabletsInStock;
	private String pharmacyName;

}
