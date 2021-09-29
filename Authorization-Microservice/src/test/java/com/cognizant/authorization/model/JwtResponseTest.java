package com.cognizant.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JwtResponseTest {

	@Mock
	public JwtResponse response;

	@Before
	public void setUp() throws Exception {
		response = new JwtResponse();
		response.setUserId("admin");
		response.setUserName("admin");
		response.setValid(false);
	}

	@Test
	public void AllArgConstTest() {
		JwtResponse auth = new JwtResponse();
		auth.setUserId("admin");
		auth.setUserName("admin");
		auth.setValid(false);
		assertEquals(response.getUserId(), auth.getUserId());
		assertEquals(response.getUserName(), auth.getUserName());
		assertEquals(response.isValid(), auth.isValid());
		
	}

	@Test
	public void testNoArgsConstructor() {
		JwtResponse response = new JwtResponse();
		assertEquals(response, response);
	}


	@Test
	public void testEqualsMethod() {
		boolean equals = response.equals(response);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = response.hashCode();
		assertEquals(hashCode, response.hashCode());
	}
}
