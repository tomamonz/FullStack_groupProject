package com.fdmgroup.gatewaydottracker.controller;

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

    public DotTraceParcelController(DotTraceParcelRequest dotTraceParcelRequest) {
        this.dotTraceParcelRequest = dotTraceParcelRequest;
    }

    @GetMapping("/number/{parcelNumber}")
    public ResponseEntity<?> findByParcelNumber(@PathVariable("parcelNumber") String parcelNumber) {
        return new ResponseEntity<>(this.dotTraceParcelRequest.findByParcelNumber(parcelNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addParcel(@Valid @RequestBody Object object) {
        return new ResponseEntity<>(this.dotTraceParcelRequest.addParcel(object), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.dotTraceParcelRequest.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.dotTraceParcelRequest.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateParcel(@Valid @RequestBody Object object) {
        return new ResponseEntity<>(this.dotTraceParcelRequest.updateParcel(object), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParcel(@PathVariable("id") Long id) {
        this.dotTraceParcelRequest.removeParcelById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/parcels/{senderId}")
    public ResponseEntity<?> findBySenderId(@PathVariable String senderId) {
        return new ResponseEntity<>(this.dotTraceParcelRequest.findBySenderId(senderId), HttpStatus.OK);
    }

}
