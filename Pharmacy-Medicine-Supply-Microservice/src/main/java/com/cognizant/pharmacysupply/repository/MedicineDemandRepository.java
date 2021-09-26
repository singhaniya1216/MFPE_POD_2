package com.cognizant.pharmacysupply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pharmacysupply.model.MedicineDemand;

/**
 * This is the MedicineDemand Repository interface extending JpaRepository.
 */
@Repository
public interface MedicineDemandRepository extends JpaRepository<MedicineDemand, Integer> {

}
