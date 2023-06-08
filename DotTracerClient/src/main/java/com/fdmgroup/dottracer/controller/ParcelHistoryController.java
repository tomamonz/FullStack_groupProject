package com.fdmgroup.dottracer.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.dottracer.model.ParcelHistory;
import com.fdmgroup.dottracer.service.ParcelHistoryServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/parcelhistory")
public class ParcelHistoryController {
	private final static Logger log = LoggerFactory.getLogger(ParcelHistoryController.class);
	private ParcelHistoryServiceImp parcelHistoryService;

	@PostMapping
	public ResponseEntity<?> addParcelHistory(@Valid @RequestBody ParcelHistory parcelHistory,
			BindingResult bindingResult) {
		log.trace("Entering addParcelHistory method");
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		log.trace("Exiting addParcelHistory method");
		return new ResponseEntity<>(this.parcelHistoryService.addParcelHistory(parcelHistory), HttpStatus.CREATED);

	}

	public ParcelHistoryController(ParcelHistoryServiceImp parcelHistoryService) {
		super();
		this.parcelHistoryService = parcelHistoryService;
	}

	@GetMapping("/{parcelNumber}")
	public ResponseEntity<?> findAllByParcelNumber(@PathVariable String parcelNumber) {
		log.trace("Entering findAllByParcelNumber method");
		ResponseEntity<?> responseEntity = new ResponseEntity<>(this.parcelHistoryService.findAllByParcelNumber(parcelNumber), HttpStatus.OK);
		log.trace("Exiting findAllByParcelNumber method");
	    return responseEntity;
	}

}
