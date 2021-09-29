package com.cognizant.pharmacysupply.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.feignclient.MedicineStockFeignClient;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.MedicineSupply;
import com.cognizant.pharmacysupply.repository.MedicineDemandRepository;
import com.cognizant.pharmacysupply.repository.MedicineSupplyRepository;

import feign.FeignException;

/**
 * This is the PharmacySupplyServiceImpl class that implements
 * PharmacySupplyService interface.
 */
@Service
public class PharmacySupplyServiceImpl implements PharmacySupplyService {

	private MedicineStockFeignClient stockFeignClient;
	private MedicineSupplyRepository medicineSupplyRepository;
	private MedicineDemandRepository medicineDemandRepository;
	private static Logger logger = LoggerFactory.getLogger(PharmacySupplyService.class);

	@Autowired
	public PharmacySupplyServiceImpl(MedicineStockFeignClient stockFeignClient,
			MedicineSupplyRepository medicineSupplyRepository,
			MedicineDemandRepository medicineDemandRepository) {
		this.stockFeignClient = stockFeignClient;
		this.medicineSupplyRepository = medicineSupplyRepository;
		this.medicineDemandRepository = medicineDemandRepository;
	}
	
	
	@Override
	public List<MedicineSupply> getPharmacySupplyCount(List<MedicineDemand> medicineDemandList)
			throws MedicineNotFoundException {
		logger.info("start");

		List<MedicineSupply> list = new ArrayList<>();
		for (MedicineDemand medicineDemand : medicineDemandList) {
			MedicineSupply pharmacyMedicineSupply = new MedicineSupply();
			MedicineStock medicineStock = getNumberOfTablets(medicineDemand);
			logger.info("STOCKLIST"+medicineStock);
			if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandCount()) {
				return null;
			}
			pharmacyMedicineSupply = setSupply(pharmacyMedicineSupply, medicineDemand, medicineStock);
			logger.info("SupplyList"+pharmacyMedicineSupply);
			list.add(pharmacyMedicineSupply);
		}
		medicineSupplyRepository.saveAll(list);
		medicineDemandRepository.saveAll(medicineDemandList);
		logger.info("end");
		return list;
	}

	private MedicineStock getNumberOfTablets(MedicineDemand medicineDemand) throws MedicineNotFoundException {
		logger.info("start");
		MedicineStock medicineStock = null;
		try {
			medicineStock = stockFeignClient.getStockCountForMedicine(medicineDemand.getMedicineName());
		} catch (FeignException ex) {
			throw new MedicineNotFoundException("Medicine not found");
		}

		if (medicineStock == null) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		logger.info("end");
		return medicineStock;
	}

	public MedicineSupply setSupply(MedicineSupply medicineSupply, MedicineDemand medicineDemand,
			MedicineStock medicineStock) throws MedicineNotFoundException {
		logger.info("start");
		int val = 0;
		medicineSupply.setSupplyCount(medicineDemand.getDemandCount());
		val = medicineStock.getNumberOfTabletsInStock() - medicineDemand.getDemandCount();
		try {
			stockFeignClient.updateNumberOfTabletsInStockByName(medicineDemand.getMedicineName(), val);
		} catch (FeignException ex) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		medicineSupply.setMedicineName(medicineDemand.getMedicineName());
		medicineSupply.setPharmacyName(medicineStock.getPharmacyName());
		logger.info("end");
		return medicineSupply;
	}


	@Override
	public List<MedicineSupply> getMedicineSupply() {
		logger.info("start");
		logger.info("end");
		return medicineSupplyRepository.findAll();
	}


	@Override
	public List<MedicineDemand> getMedicineDemand() {
		logger.info("start");
		logger.info("end");
		return medicineDemandRepository.findAll();
	}

}
