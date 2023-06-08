package com.fdmgroup.dottracer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.dottracer.model.User;
import com.fdmgroup.dottracer.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
	private UserRepository userRepository;

	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);

	}

}
