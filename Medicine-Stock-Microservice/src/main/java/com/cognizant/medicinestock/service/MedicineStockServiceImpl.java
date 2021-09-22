package com.cognizant.medicinestock.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.medicinestock.exception.MedicineNotFoundException;
import com.cognizant.medicinestock.model.MedicineStock;
import com.cognizant.medicinestock.repository.MedicineStockRepository;

@Service
public class MedicineStockServiceImpl implements MedicineStockService {

	private MedicineStockRepository repository;

	@Autowired
	public MedicineStockServiceImpl(MedicineStockRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<MedicineStock> getMedicineStockInformation() {
		return repository.findAll();
	}

	@Override
	public List<String> findByTargetAilment(String targetAilment) throws MedicineNotFoundException {
		List<MedicineStock> list = repository.findByTargetAilment(targetAilment);
		if (list.isEmpty()) {
			throw new MedicineNotFoundException("No medicine for this Target Ailment available.");
		} else {
			return list.stream().map(MedicineStock::getName).collect(Collectors.toList());
		}
	}

	@Override
	public MedicineStock findByName(String name) throws MedicineNotFoundException {
		Optional<MedicineStock> medicine = repository.findByName(name);
		if (medicine.isEmpty()) {
			throw new MedicineNotFoundException("No medicine of the given name is available.");
		} else {
			return medicine.get();
		}
	}

	@Override
	public Boolean updateStockByName(String name, int count) throws MedicineNotFoundException {
		MedicineStock medicine = findByName(name);
		medicine.setNumberOfTabletsInStock(count);
		repository.save(medicine);
		return true;
	}

}
