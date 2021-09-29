package com.cognizant.authorization.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDetailTest {

	@Mock
	private UserDetail userDetail;

	@Before
	public void setUp() {
		userDetail = new UserDetail();
		userDetail.setPassword("admin");
		userDetail.setUserId("admin");
		userDetail.setUserName("admin");
	}


	@Test
	public void testNoArgumentConstructor() {
		UserDetail user = new UserDetail();
		assertEquals(user, user);
	}
	
	@Test
	public void testGet() {
		UserDetail user = new UserDetail();
		user.setPassword(userDetail.getPassword());
		user.setUserId(userDetail.getUserId());
		user.setUserName(userDetail.getUserName());
		assertEquals(user.getPassword(), userDetail.getPassword());
	}

	@Test
	public void testToStringMethod() {
		assertEquals(userDetail.toString(), userDetail.toString());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = userDetail.equals(userDetail);
		assertTrue(equals);
	}

}
