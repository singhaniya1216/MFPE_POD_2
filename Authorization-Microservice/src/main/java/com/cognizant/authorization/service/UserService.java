package com.cognizant.authorization.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cognizant.authorization.model.UserDetail;
import com.cognizant.authorization.exception.UserNotFoundException;
import com.cognizant.authorization.repository.UserDetailRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserDetailRepository userdao;

	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		Optional<UserDetail> user = userdao.findById(uid);
		if (user.isEmpty()) {
			throw new UserNotFoundException("Unauthenticated User. Try again with valid credentials.");
		} else {
			return new User(user.get().getUserId(), user.get().getPassword(), new ArrayList<>());
		}

	}

}
