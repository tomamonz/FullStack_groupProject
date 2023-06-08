package com.fdmgroup.gatewaydottracker.request;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "dottracer", path = "/api/v1/users")
public interface DotTraceUserRequest {

    @PostMapping
    Object addUser(Object object);

    @GetMapping("/{email}/{password}")
    Optional<Object> findUserByEmailAndPassword(@PathVariable("email") String email,
            @PathVariable("password") String password);

    @GetMapping("/{email}")
    Optional<Object> findUserByEmail(@PathVariable("email") String email);

}
