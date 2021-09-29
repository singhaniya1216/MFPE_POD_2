package com.cognizant.pharmacysupply.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * This is the entity class for Medicine Supply having fields id, pharmacyName,
 * medicineName, supplyCount.
 */

@Getter
@Setter
@Entity
@ToString
@Table(name="medicine_supply")
@NoArgsConstructor
public class MedicineSupply {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int id;
	private String pharmacyName;
	private String medicineName;
	private int supplyCount;
}
