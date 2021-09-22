package com.cognizant.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


@SpringBootTest
public class ErrorResponseTest {
	
	@Mock
	private ErrorResponse res;

	@Before
	public void setup() {	
		res = new ErrorResponse();
		res.setStatus(HttpStatus.BAD_REQUEST);
		res.setReason("BAD REQUEST");
		res.setMessage("Please provide valid value.");	
	}


	@Test
	public void testErrorResponse() throws Exception {
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatus());
		assertEquals("BAD REQUEST", res.getReason());
		assertEquals("Please provide valid value.", res.getMessage());
	}


	@Test
	public void testAllArgsConstructor() {
		ErrorResponse res = new ErrorResponse(null, HttpStatus.OK, "Bad request",
				"Please provide valid value");
		assertEquals("Bad request", res.getReason());
	}

	@Test
	public void testToStringMethod() {
		ErrorResponse test = new ErrorResponse(null, HttpStatus.BAD_REQUEST, "BAD REQUEST",
				"Please provide valid value.");
		assertEquals(res.toString(), test.toString());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = res.equals(res);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = res.hashCode();
		assertEquals(hashCode, res.hashCode());
	}

}
