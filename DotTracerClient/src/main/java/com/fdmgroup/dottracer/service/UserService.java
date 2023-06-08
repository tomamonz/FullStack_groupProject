package com.fdmgroup.dottracer.service;

import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.fdmgroup.dottracer.model.User;

public interface UserService {

	Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	User addUser(User user);

	Optional<User> findByEmail(@Param("email") String email);
}
