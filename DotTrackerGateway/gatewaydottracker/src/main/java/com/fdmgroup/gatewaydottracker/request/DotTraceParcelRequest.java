package com.fdmgroup.gatewaydottracker.request;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "dottracer", path = "/api/v1/parcels")
public interface DotTraceParcelRequest {

    @GetMapping("/number/{parcelNumber}")
    Object findByParcelNumber(@PathVariable("parcelNumber") String parcelNumber);

    @PostMapping
    Object addParcel(Object object);

    @GetMapping
    Iterable<Object> findAll();

    @GetMapping("/{id}")
    Optional<Object> findById(@PathVariable("id") Long id);

    @PutMapping
    Optional<Object> updateParcel(Object object);

    @DeleteMapping("/{id}")
    Void removeParcelById(@PathVariable() Long id);

    @GetMapping("/parcels/{senderId}")
    List<Object> findBySenderId(@PathVariable("senderId") String senderId);

}
