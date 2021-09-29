package com.cognizant.pharmacysupply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pharmacysupply.model.MedicineSupply;

/**
 * This is the MedicineSupply Repository interface extending JpaRepository.
 */
@Repository
public interface MedicineSupplyRepository extends JpaRepository<MedicineSupply, Integer> {

}
