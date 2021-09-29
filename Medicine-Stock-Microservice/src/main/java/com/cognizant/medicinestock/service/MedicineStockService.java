package com.cognizant.medicinestock.service;

import java.util.List;

import com.cognizant.medicinestock.exception.MedicineNotFoundException;
import com.cognizant.medicinestock.model.MedicineStock;

/**
 * This is the MedicineStockService interface containing declaration of the
 * methods.
 */
public interface MedicineStockService {

	public List<MedicineStock> getMedicineStockInformation();

	public List<String> findByTargetAilment(String targetAilment) throws MedicineNotFoundException;

	public MedicineStock findByName(String name) throws MedicineNotFoundException;

	public Boolean updateStockByName(String name, int count) throws MedicineNotFoundException;
}
