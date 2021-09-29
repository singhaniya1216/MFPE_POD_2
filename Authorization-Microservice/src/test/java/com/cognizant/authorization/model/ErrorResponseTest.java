package com.cognizant.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
		LocalDateTime ldt=LocalDateTime.now();
		res.setTimestamp(ldt);
		res.setStatus(HttpStatus.BAD_REQUEST);
		res.setReason("BAD REQUEST");
		res.setMessage("Please provide valid value.");	
	}

	@Test
	public void testErrorResponse() throws Exception {
		assertEquals(res.getTimestamp(), res.getTimestamp());
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatus());
		assertEquals("BAD REQUEST", res.getReason());
		assertEquals("Please provide valid value.", res.getMessage());
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
