package com.fdmgroup.dottracer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.dottracer.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

	Optional<User> findByEmail(@Param("email") String email);

}
