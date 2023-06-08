package com.fdmgroup.dottracer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.dottracer.model.User;
import com.fdmgroup.dottracer.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<?> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.userService.addUser(user), HttpStatus.CREATED);
	}

	@GetMapping("/{email}/{password}")
	public ResponseEntity<?> findByEmailAndPassword(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		Optional<User> user = this.userService.findByEmailAndPassword(email, password);

		if (user.isEmpty()) {
			return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}

	@GetMapping("/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
		Optional<User> user = this.userService.findByEmail(email);

		if (user.isEmpty()) {
			return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}

}
