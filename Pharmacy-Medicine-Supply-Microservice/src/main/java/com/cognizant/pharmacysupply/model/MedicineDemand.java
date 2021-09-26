package com.cognizant.pharmacysupply.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

/**
 * This is the entity class for Medicine Demand having fields id, medicineName,
 * demandCount.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="medicine_demand")
public class MedicineDemand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message = "Medicine must not be empty")
	@NotNull(message = "Medicine must not be null")
	private String medicineName;
	
	private int demandCount;
}
