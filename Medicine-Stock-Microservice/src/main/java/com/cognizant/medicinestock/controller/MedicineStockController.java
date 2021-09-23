package com.cognizant.medicinestock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicinestock.exception.MedicineNotFoundException;
import com.cognizant.medicinestock.model.MedicineStock;
import com.cognizant.medicinestock.service.MedicineStockServiceImpl;

@RestController
public class MedicineStockController {

	private MedicineStockServiceImpl medicineStockService;

	@Autowired
	public MedicineStockController(MedicineStockServiceImpl medicineStockService) {
		this.medicineStockService = medicineStockService;
	}

	@GetMapping("/medicineStockInformation")
	public ResponseEntity<List<MedicineStock>> medicineStockInformation() {
		return ResponseEntity.ok(medicineStockService.getMedicineStockInformation());
	}

	@PostMapping("/byTreatingAilment/{treatingAilment}")
	public ResponseEntity<List<String>> getMedicineByTreatingAilment(
			@PathVariable("treatingAilment") String treatingAilment) throws MedicineNotFoundException {
		return ResponseEntity.ok(medicineStockService.findByTargetAilment(treatingAilment));
	}

	
	@PostMapping("/getStockCount/{medicine}")
	public ResponseEntity<MedicineStock> getStockCountForMedicine(@PathVariable("medicine") String medicine)
			throws MedicineNotFoundException {
		return ResponseEntity.ok(medicineStockService.findByName(medicine));
	}

	@PostMapping("/updateStock/{medicine}/{count}")
	public ResponseEntity<Boolean> updateNumberOfTabletsInStockByName(@PathVariable("medicine") String medicine,
			@PathVariable("count") int count) throws MedicineNotFoundException {
		return ResponseEntity.ok(medicineStockService.updateStockByName(medicine, count));
	}

}
