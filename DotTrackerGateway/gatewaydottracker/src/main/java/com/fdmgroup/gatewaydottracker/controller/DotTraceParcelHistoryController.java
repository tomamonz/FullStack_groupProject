package com.fdmgroup.gatewaydottracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.gatewaydottracker.request.DotTraceParcelHistoryRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gateway/parcelHistory")
public class DotTraceParcelHistoryController {

    private DotTraceParcelHistoryRequest dotTraceParcelHistoryRequest;
    private final Logger logger = LoggerFactory.getLogger(DotTraceParcelHistoryRequest.class);

    public DotTraceParcelHistoryController(DotTraceParcelHistoryRequest dotTraceParcelHistoryRequest) {
        this.dotTraceParcelHistoryRequest = dotTraceParcelHistoryRequest;
    }

    @PostMapping
    public ResponseEntity<?> addParcelHistory(@Valid @RequestBody Object object) {
        logger.info("Request received to add parcel history: {}", object);

        Object result = this.dotTraceParcelHistoryRequest.addParcelHistory(object);

        logger.info("Response after adding parcel history: {}", result);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/{parcelNumber}")
    public ResponseEntity<?> findAllByParcelNumber(@PathVariable("parcelNumber") String parcelNumber) {
        logger.info("Request received to find all parcel history by parcel number: {}", parcelNumber);

        Iterable<Object> result = this.dotTraceParcelHistoryRequest.findAllByParcelNumber(parcelNumber);

        logger.info("Response for finding all parcel history by parcel number {}: {}", parcelNumber, result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
