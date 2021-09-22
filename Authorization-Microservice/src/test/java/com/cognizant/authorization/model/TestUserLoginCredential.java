package com.cognizant.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestUserLoginCredential {

	@Mock
	public UserLoginCredential userLogin;

	@Before
	public void setUp() {
		userLogin = new UserLoginCredential("admin", "admin");
	}

	@Test
	public void testAllArgumentConstructor() {
		UserLoginCredential userLog = new UserLoginCredential("admin", "admin");
		assertEquals("admin", userLog.getUserId());
	}

	@Test
	public void testEquals() {
		boolean res = userLogin.equals(userLogin);
		assertTrue(res);
	}

	@Test
	public void testNoArgConstructor() {
		UserLoginCredential ulc = new UserLoginCredential();
		assertEquals(ulc, ulc);
	}

	@Test
	public void testToStringMethod() {
		assertEquals(userLogin.toString(), userLogin.toString());
	}
}
