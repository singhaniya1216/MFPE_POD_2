package com.cognizant.medicinestock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.medicinestock.exception.MedicineNotFoundException;
import com.cognizant.medicinestock.model.MedicineStock;
import com.cognizant.medicinestock.service.MedicineStockServiceImpl;

/**
 * This is the controller class of Medicine-Stock
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class MedicineStockController {

	private MedicineStockServiceImpl medicineStockService;
	
	private static Logger logger = LoggerFactory.getLogger(MedicineStockController.class);

	@Autowired
	public MedicineStockController(MedicineStockServiceImpl medicineStockService) {
		this.medicineStockService = medicineStockService;
	}

	/**
	 * This method returns all the details of the medicine present in stock.
	 * 
	 * @return
	 */
	@GetMapping("/MedicineStockInformation")
	public ResponseEntity<List<MedicineStock>> medicineStockInformation() {
		logger.info("start");
		logger.info("end");
		return ResponseEntity.ok(medicineStockService.getMedicineStockInformation());
	}

	/**
	 * This method returns the medicine by checking the given treatingAilment
	 * 
	 * @param treatingAilment
	 * @return
	 * @throws MedicineNotFoundException
	 */
	@PostMapping("/byTreatingAilment/{treatingAilment}")
	public ResponseEntity<List<String>> getMedicineByTreatingAilment(
			@PathVariable("treatingAilment") String treatingAilment) throws MedicineNotFoundException {
		logger.info("start");
		logger.info("end");
		return ResponseEntity.ok(medicineStockService.findByTargetAilment(treatingAilment));
	}

	/**
	 * This method returns the medicine stock count of the given medicine
	 * 
	 * @param medicine
	 * @return
	 * @throws MedicineNotFoundException
	 */
	@PostMapping("/getStockCount/{medicine}")
	public ResponseEntity<MedicineStock> getStockCountForMedicine(@PathVariable("medicine") String medicine)
			throws MedicineNotFoundException {
		logger.info("start");
		logger.info("end");
		return ResponseEntity.ok(medicineStockService.findByName(medicine));
	}

	/**
	 * This method updates the stock count of the given medicine.
	 * 
	 * @param medicine
	 * @param count
	 * @return
	 * @throws MedicineNotFoundException
	 */
	@PostMapping("/updateStock/{medicine}/{count}")
	public ResponseEntity<Boolean> updateNumberOfTabletsInStockByName(@PathVariable("medicine") String medicine,
			@PathVariable("count") int count) throws MedicineNotFoundException {
		logger.info("start");
		logger.info("end");
		return ResponseEntity.ok(medicineStockService.updateStockByName(medicine, count));
	}

}
