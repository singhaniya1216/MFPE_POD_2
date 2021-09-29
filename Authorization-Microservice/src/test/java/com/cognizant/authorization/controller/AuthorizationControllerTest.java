package com.cognizant.authorization.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.authorization.exception.UserNotFoundException;
import com.cognizant.authorization.model.UserLoginCredential;
import com.cognizant.authorization.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class AuthorizationControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testValidLogin() throws UserNotFoundException, Exception {
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
	public void testInvalidLogin_IncorrectUserId() throws UserNotFoundException, Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("deepak");
		anObject.setPassword("aman");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isForbidden());
	}

	@Test
	public void testInvalidLogin_IncorrectPassword() throws UserNotFoundException, Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("deepak");
		// ... more
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(anObject);
		this.mockMvc.perform(post("/login").contentType(APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testValidToken() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		final UserDetails userdetails = new User(anObject.getUserId(), anObject.getPassword(), new ArrayList<>());
		JwtUtil jwt = new JwtUtil();
		final String token = "Bearer "+jwt.generateToken(userdetails);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/validate").header("Authorization",token)).andExpect(status().isOk());
	}
	
	@Test
	public void testValidInvalidToken() throws Exception {
		UserLoginCredential anObject = new UserLoginCredential();
		anObject.setUserId("aman");
		anObject.setPassword("aman");
		final UserDetails userdetails = new User(anObject.getUserId(), anObject.getPassword(), new ArrayList<>());
		JwtUtil jwt = new JwtUtil();
		final String token1 = "Be"+jwt.generateToken(userdetails);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/validate").header("Authorization",token1)).andExpect(status().isBadRequest());
	}
}
