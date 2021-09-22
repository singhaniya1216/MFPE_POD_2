package com.cognizant.medicalrepresentative.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.medicalrepresentative.model.MedicalRepresentative;

@Repository
public interface MedicalRepresentativeRepository extends JpaRepository<MedicalRepresentative, Integer> {

}
