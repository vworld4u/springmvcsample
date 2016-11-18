package com.vworld4u.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vworld4u.models.User;
import com.vworld4u.repositories.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User register(User user) throws Exception {
		log.info("registerUser: " + user);
		User exUser = userRepository.findByEmail(user.getEmail());
		log.info("registerUser: ExistingUser = " + exUser);
		if (exUser != null) {
			throw new RuntimeException("Already registered with email : " + user.getEmail());
		}
		log.debug("registerUser: Saving user first time : " + user);
		User savedUser = userRepository.save(user);
		log.debug("registerUser: savedUser : " + savedUser);
		return savedUser;
	}

	@Override
	public User editUser(User user) {
		User exUser = userRepository.findByEmail(user.getEmail());
		if (exUser == null) {
			throw new RuntimeException("Already registered with email : " + user.getEmail());
		}
		exUser.copyFrom(user);
		User savedUser = userRepository.save(exUser);
		return savedUser;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findOne(id);
	}

}
