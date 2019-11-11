package com.ysnerruk.api.model;

import com.ysnerruk.api.model.User;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testGetAndSetId() {
		User user = new User();
		assertNull(user.getId());
		Integer id = Integer.valueOf(10);
		user.setId(id);
		assert(user.getId().equals(id));
	}
	
	@Test
	public void testGetAndSetUsername() {
		User user = new User();
		assertNull(user.getUsername());
		String username = "anaemeee";
		user.setUsername(username);
		assert(user.getUsername().equals(username));
	}

	@Test
	public void testGetAndSetEmail() {
		User user = new User();
		assertNull(user.getEmail());
		String email = "anaemeee@mail.com";
		user.setEmail(email);
		assert(user.getEmail().equals(email));
	}
	
	@Test
	public void testGetAndSetPassword() {
		User user = new User();
		assertNull(user.getPassword());
		String pass = "super_secret";
		user.setPassword(pass);
		assert(user.getPassword().equals(pass));
	}
}
