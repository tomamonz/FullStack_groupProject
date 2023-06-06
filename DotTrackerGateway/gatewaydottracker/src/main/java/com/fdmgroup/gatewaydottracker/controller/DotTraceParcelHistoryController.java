package com.fdmgroup.gatewaydottracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.gatewaydottracker.request.DotTraceParcelHistoryRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gateway/dotTrace/parcelHistory")
public class DotTraceParcelHistoryController {

    private DotTraceParcelHistoryRequest dotTraceParcelHistoryRequest;

    public DotTraceParcelHistoryController(DotTraceParcelHistoryRequest dotTraceParcelHistoryRequest) {
        this.dotTraceParcelHistoryRequest = dotTraceParcelHistoryRequest;
    }

    @PostMapping
    public ResponseEntity<?> addParcelHistory(@Valid @RequestBody Object object) {
        return new ResponseEntity<>(this.dotTraceParcelHistoryRequest.addParcelHistory(object), HttpStatus.CREATED);
    }

}
