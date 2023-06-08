package com.fdmgroup.gatewaydottracker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.gatewaydottracker.request.DotTraceParcelRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/gateway/parcels")
public class DotTraceParcelController {

    private DotTraceParcelRequest dotTraceParcelRequest;
    private final Logger logger = LoggerFactory.getLogger(DotTraceParcelController.class);

    public DotTraceParcelController(DotTraceParcelRequest dotTraceParcelRequest) {
        this.dotTraceParcelRequest = dotTraceParcelRequest;
    }

    @GetMapping("/number/{parcelNumber}")
    public ResponseEntity<?> findByParcelNumber(@PathVariable("parcelNumber") String parcelNumber) {
        logger.info("Request received for parcel number: {}", parcelNumber);
        Object result = this.dotTraceParcelRequest.findByParcelNumber(parcelNumber);
        logger.info("Response for parcel number {}: {}", parcelNumber, result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addParcel(@Valid @RequestBody Object object) {
        logger.info("Request received to add parcel: {}", object);

        Object result = this.dotTraceParcelRequest.addParcel(object);

        logger.info("Response after adding parcel: {}", result);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        logger.info("Request received to find all parcels.");

        Object result = this.dotTraceParcelRequest.findAll();

        logger.info("Response for finding all parcels: {}", result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        logger.info("Request received to find parcel by ID: {}", id);

        Object result = this.dotTraceParcelRequest.findById(id);

        logger.info("Response for finding parcel by ID {}: {}", id, result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateParcel(@Valid @RequestBody Object object) {
        logger.info("Request received to update parcel: {}", object);

        Object result = this.dotTraceParcelRequest.updateParcel(object);

        logger.info("Response after updating parcel: {}", result);

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParcel(@PathVariable("id") Long id) {
        logger.info("Request received to delete parcel with ID: {}", id);

        this.dotTraceParcelRequest.removeParcelById(id);

        logger.info("Parcel with ID {} deleted successfully.", id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/parcels/{senderId}")
    public ResponseEntity<?> findBySenderId(@PathVariable String senderId) {
        logger.info("Request received to find parcels by sender ID: {}", senderId);

        Object result = this.dotTraceParcelRequest.findBySenderId(senderId);

        logger.info("Response for finding parcels by sender ID {}: {}", senderId, result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
