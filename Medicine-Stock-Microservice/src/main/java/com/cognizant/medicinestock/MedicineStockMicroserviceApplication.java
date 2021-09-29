package com.cognizant.medicinestock;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.medicinestock.model.MedicineStock;
import com.cognizant.medicinestock.repository.MedicineStockRepository;

/**
 * This is the application class of Medicine-Stock Microservice.
 */
@SpringBootApplication
@EnableFeignClients
public class MedicineStockMicroserviceApplication implements CommandLineRunner {

	@Autowired
	private MedicineStockRepository medicineStockRepository;

	public static void main(String[] args) {
		SpringApplication.run(MedicineStockMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		medicineStockRepository
				.save(new MedicineStock(1, "Crocin", "Disaccharide Gentiobiose, Dicarboxylic Acid Crocetin", "General",
						LocalDate.parse("2023-05-25"), 200, "Apollo Pharmacy"));
		medicineStockRepository.save(new MedicineStock(2, "Dolo65", "Acetaminophen", "General",
				LocalDate.parse("2022-06-21"), 500, "Medanta Drugs"));
		medicineStockRepository.save(new MedicineStock(3, "Gaviscon", "Sodium Bicarbonate, Sodium Alginate", "General",
				LocalDate.parse("2023-08-25"), 250, "Apollo Pharmacy"));
		medicineStockRepository.save(new MedicineStock(4, "Orthoherb", "Castor Plant, Neem", "Orthopaedics",
				LocalDate.parse("2022-01-25"), 200, "Medanta Drugs"));
		medicineStockRepository.save(new MedicineStock(5, "Cyclopam", "Dicyclomine, Paracetamol", "Gynaecology",
				LocalDate.parse("2022-01-25"), 300, "Apollo Pharmacy"));
		medicineStockRepository.save(new MedicineStock(6, "Cholecalciferol", "Dehydrocholesterol", "Orthopaedics",
				LocalDate.parse("2022-01-25"), 100, "Jay Pharma"));
		medicineStockRepository.save(new MedicineStock(7, "Locoid", "Hydrocortisone", "Dermatalogy",
				LocalDate.parse("2022-01-25"), 100, "Jay Pharma"));
		medicineStockRepository.save(new MedicineStock(8, "Hilact", "Magnesium Stearate", "Gynaecology",
				LocalDate.parse("2022-01-25"), 300, "Medanta Drugs"));
		medicineStockRepository.save(new MedicineStock(9, "ApexiCon", "Diflorasone Topical", "Dermatalogy",
				LocalDate.parse("2022-01-25"), 200, "Apollo Pharmacy"));

	}
}
