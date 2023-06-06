package com.fdmgroup.gatewaydottracker.request;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "dot-trace1", path = "/api/v1/parcelhistory")
public interface DotTraceParcelHistoryRequest {

    @PostMapping
    Object addParcelHistory(Object object);

}
