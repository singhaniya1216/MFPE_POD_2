package com.cognizant.medicalrepresentative.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.medicalrepresentative.model.MedicalRepresentative;

/**
 * This is a Repository interface which is extending JpaRepository for
 * MedicalRepresentative Models.
 */
@Repository
public interface MedicalRepresentativeRepository extends JpaRepository<MedicalRepresentative, Integer> {

}
