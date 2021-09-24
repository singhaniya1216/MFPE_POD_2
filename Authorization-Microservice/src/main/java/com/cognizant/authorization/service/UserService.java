package com.cognizant.authorization.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authorization.exception.UserNotFoundException;
import com.cognizant.authorization.model.UserDetail;
import com.cognizant.authorization.repository.UserDetailRepository;

/**
 * This class fetches the user details from the database and verify whether the
 * user details are there in the database or not based on the given id. This
 * class implements the UserDetailsService interface.
 */
@Service
public class UserService implements UserDetailsService {

	private UserDetailRepository userdao;

	@Autowired
	public UserService(UserDetailRepository userdao) {
		this.userdao = userdao;
	}

	private static Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

	/**
	 * This method loads the user details by verifying with userId, and throws an
	 * exception if the user is not found.
	 */
	@Override
	public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
		logger.info("start");
		logger.info("end");
		Optional<UserDetail> user = userdao.findById(uid);
		if (user.isEmpty()) {
			throw new UserNotFoundException("Unauthenticated User. Try again with valid credentials.");
		} else {
			return new User(user.get().getUserId(), user.get().getPassword(), new ArrayList<>());
		}

	}

}
