package com.cognizant.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserTokenTest {

	@Mock
	private UserToken userToken;

	@Before
	public void setUp() {
		userToken = new UserToken("admin", "Token");
	}

	@Test
	public void testAllArgumentConstructor() {
		UserToken userTokn = new UserToken("admin", "Token");
		assertEquals("Token", userTokn.getAuthToken());
		assertEquals("admin", userTokn.getUserId());
	}

	@Test
	public void testEquals() {
		boolean res = userToken.equals(userToken);
		assertTrue(res);
	}

	@Test
	public void testToStringMethod() {
		assertEquals(userToken.toString(), userToken.toString());
	}

}
