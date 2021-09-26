package com.cognizant.medicinestock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cognizant.medicinestock.model.MedicineStock;

/**
 * This is a Repository interface which is extending JpaRepository.
 */
@Repository
public interface MedicineStockRepository extends JpaRepository<MedicineStock, Integer> {

	@Query
	public List<MedicineStock> findByTargetAilment(String targetAilment);

	@Query
	public Optional<MedicineStock> findByName(String name);
}
