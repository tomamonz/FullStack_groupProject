package com.fdmgroup.dottracer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.dottracer.model.ParcelHistory;
import com.fdmgroup.dottracer.service.ParcelHistoryServiceImp;

@RestController
@RequestMapping("/api/v1/parcelhistory")
public class ParcelHistoryController {

	private ParcelHistoryServiceImp parcelHistoryService;

	@PostMapping
	public ResponseEntity<?> addParcelHistory(ParcelHistory parcelHistory, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.parcelHistoryService.addParcelHistory(parcelHistory), HttpStatus.CREATED);

	}
}
