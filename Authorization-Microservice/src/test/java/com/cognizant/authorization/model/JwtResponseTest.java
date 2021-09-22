package com.cognizant.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtResponseTest {

	@Mock
	public JwtResponse response;

	@Before
	public void setUp() throws Exception {
		response = new JwtResponse();
		response.setUserId("admin");
		response.setUserId("admin");
		response.setValid(false);
	}

	@Test
	public void AllArgConstTest() {
		JwtResponse auth = new JwtResponse("admin", "admin", false);
		assertEquals(response.getUserId(), auth.getUserId());
		assertEquals("admin", auth.getUserId());
	}

	@Test
	public void testToStringMethod() {
		JwtResponse res = new JwtResponse();
		res.setUserId("admin");
		res.setUserId("admin");
		res.setValid(false);
		assertEquals(res.toString(), response.toString());
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
