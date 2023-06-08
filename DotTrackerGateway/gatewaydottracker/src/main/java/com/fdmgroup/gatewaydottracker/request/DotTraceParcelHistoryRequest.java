package com.fdmgroup.gatewaydottracker.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "dottracer", path = "/api/v1/parcelhistory")
public interface DotTraceParcelHistoryRequest {

    @PostMapping
    Object addParcelHistory(Object object);

    @GetMapping("/{parcelNumber}")
    Iterable<Object> findAllByParcelNumber(@PathVariable("parcelNumber") String parcelNumber);

}
