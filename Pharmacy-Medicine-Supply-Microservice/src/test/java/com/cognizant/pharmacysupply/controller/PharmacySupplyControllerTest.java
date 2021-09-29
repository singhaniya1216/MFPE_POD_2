package com.cognizant.pharmacysupply.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.UserLoginCredential;
import com.cognizant.pharmacysupply.model.UserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class PharmacySupplyControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void loginPortalTest() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isOk());
	}
	
	@Test
	public void loginPortalTestInvalidUserID() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("deepak");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void loginPortalTestInvalidPassword() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("deepak");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isBadRequest()).andReturn();
		
	}

	@Test
	public void testSchedule() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		MvcResult ut = this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String responseAsString = ut.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserToken myResponse = objectMapper.readValue(responseAsString, UserToken.class);
		String token = myResponse.getAuthToken();
		final String token1 = "Bearer "+token;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/RepSchedule/2021-11-20").header("Authorization",token1)).andExpect(status().isOk());
	}
	
	@Test
	public void testDemand() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		MvcResult ut = this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String responseAsString = ut.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserToken myResponse = objectMapper.readValue(responseAsString, UserToken.class);
		String token = myResponse.getAuthToken();
		final String token1 = "Bearer "+token;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getMedicineDemand").header("Authorization",token1)).andExpect(status().isOk());
	}
	
	@Test
	public void testDemandInvalidToken() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		MvcResult ut = this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String responseAsString = ut.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserToken myResponse = objectMapper.readValue(responseAsString, UserToken.class);
		String token = myResponse.getAuthToken();
		final String token1 = "Bearer"+token;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getMedicineDemand").header("Authorization",token1)).andExpect(status().isForbidden());
	}
	
	@Test
	public void testSupply() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		MvcResult ut = this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String responseAsString = ut.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserToken myResponse = objectMapper.readValue(responseAsString, UserToken.class);
		String token = myResponse.getAuthToken();
		final String token1 = "Bearer "+token;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getMedicineSupply").header("Authorization",token1)).andExpect(status().isOk());
	}
	
	
	@Test
	public void testSupplyInvalidToken() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		MvcResult ut = this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String responseAsString = ut.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserToken myResponse = objectMapper.readValue(responseAsString, UserToken.class);
		String token = myResponse.getAuthToken();
		final String token1 = "Bearer"+token;
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getMedicineSupply").header("Authorization",token1)).andExpect(status().isForbidden());
	}
	
	
	@Test
	public void testPharmacySupply() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		MvcResult ut = this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String responseAsString = ut.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserToken myResponse = objectMapper.readValue(responseAsString, UserToken.class);
		String token = myResponse.getAuthToken();
		final String token1 = "Bearer "+token;
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List medicineDemandList = new ArrayList();
		MedicineDemand md = new MedicineDemand();
		md.setDemandCount(5);
		md.setMedicineName("Crocin");
		medicineDemandList.add(md);
		String json = gson.toJson(medicineDemandList); 
		this.mockMvc.perform(MockMvcRequestBuilders.post("/PharmacySupply").header("Authorization",token1).contentType(APPLICATION_JSON_UTF8).content(json)).andExpect(status().isOk());
	}
	
	@Test
	public void testPharmacySupplyWrongMedicine() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		MvcResult ut = this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson)).andReturn();
		String responseAsString = ut.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		UserToken myResponse = objectMapper.readValue(responseAsString, UserToken.class);
		String token = myResponse.getAuthToken();
		final String token1 = "Bearer "+token;
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List medicineDemandList = new ArrayList();
		MedicineDemand md = new MedicineDemand();
		md.setId(1);
		md.setDemandCount(50);
		md.setMedicineName("Croci");
		medicineDemandList.add(md);
		String json = gson.toJson(medicineDemandList); 
		this.mockMvc.perform(MockMvcRequestBuilders.post("/PharmacySupply").header("Authorization",token1).contentType(APPLICATION_JSON_UTF8).content(json)).andExpect(status().isForbidden());
	}


}
