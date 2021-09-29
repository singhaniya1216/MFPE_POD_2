package com.cognizant.pharmacysupply.service;

import java.util.List;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineSupply;

/**
 * This is the PharmacySupplyService interface.
 */
public interface PharmacySupplyService {
	public List<MedicineSupply> getPharmacySupplyCount(List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException;
	public List<MedicineSupply> getMedicineSupply();
	public List<MedicineDemand> getMedicineDemand();
}
