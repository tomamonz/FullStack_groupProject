package com.fdmgroup.gatewaydottracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.gatewaydottracker.request.DotTraceUserRequest;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/gateway/users")
public class DotTraceUserController {

    private DotTraceUserRequest dotTraceUserRequest;
    private final Logger logger = LoggerFactory.getLogger(DotTraceUserRequest.class);

    public DotTraceUserController(DotTraceUserRequest dotTraceUserRequest) {
        this.dotTraceUserRequest = dotTraceUserRequest;
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<?> findByEmailAndPassword(@PathVariable("email") String email,
            @PathVariable("password") String password) {
        logger.info("Request received to find user by email and password: email={}, password={}", email, password);

        Object result = this.dotTraceUserRequest.findUserByEmailAndPassword(email, password);

        logger.info("Response for finding user by email and password: {}", result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody Object object) {
        logger.info("Request received to add user: {}", object);

        Object result = this.dotTraceUserRequest.addUser(object);

        logger.info("Response after adding user: {}", result);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        logger.info("Request received to find user by email: {}", email);

        Object user = this.dotTraceUserRequest.findUserByEmail(email);

        if (user != null) {
            logger.info("Response for finding user by email: {}", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            logger.info("User with email {} not found.", email);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
