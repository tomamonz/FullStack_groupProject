package com.fdmgroup.gatewaydottracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.gatewaydottracker.request.DotTraceUserRequest;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/gateway/users")
public class DotTraceUserController {

    private DotTraceUserRequest dotTraceUserRequest;

    public DotTraceUserController(DotTraceUserRequest dotTraceUserRequest) {
        this.dotTraceUserRequest = dotTraceUserRequest;
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<?> findByEmailAndPassword(@PathVariable("email") String email,
            @PathVariable("password") String password) {
        return new ResponseEntity<>(this.dotTraceUserRequest.findUserByEmailAndPassword(email, password),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody Object object) {
        return new ResponseEntity<>(this.dotTraceUserRequest.addUser(object), HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        Object user = this.dotTraceUserRequest.findUserByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
