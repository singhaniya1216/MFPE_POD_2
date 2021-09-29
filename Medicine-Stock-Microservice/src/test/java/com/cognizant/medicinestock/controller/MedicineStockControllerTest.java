package com.cognizant.medicinestock.controller;

import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class MedicineStockControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;


	@Test
	public void TestMedicineStockInformationTest() throws Exception {
		log.info("Start");
		this.mockMvc.perform(get("/MedicineStockInformation")).andExpect(status().isOk());
		log.info("end");
	}

	@Test
	public void TestGetMedicineByTreatingAilment() throws Exception {
		log.info("Start");
		///byTreatingAilment/{treatingAilment}
		this.mockMvc.perform(
				post("/byTreatingAilment/General")
				).andExpect(status().isOk());
		log.info("end");

	}
	
	@Test
	public void updateNumberOfTabletsInStockByNamee() throws Exception {
		log.info("Start");
		///updateStock/{medicine}/{count}
         this.mockMvc.perform(
				post("/updateStock/Crocin/1000")
				)
		.andExpect(status().isOk());
		log.info("end");
	}

	@Test
	public void getStockCountForMedicine() throws Exception {
		log.info("Start");
		////getStockCount/{medicine}
         this.mockMvc.perform(
				post("/getStockCount/Crocin")
				)
		.andExpect(status().isOk());
		log.info("end");

	}
	
	@Test
	public void getStockCountForMedicineWrong() throws Exception {
		log.info("Start");
		////getStockCount/{medicine}
         this.mockMvc.perform(
				post("/getStockCount/Crosin")
				)
		.andExpect(status().isNotFound());
		log.info("end");
	}

	
	

}
