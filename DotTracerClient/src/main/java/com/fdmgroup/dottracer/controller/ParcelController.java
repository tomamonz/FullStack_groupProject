package com.fdmgroup.dottracer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.dottracer.model.Parcel;
import com.fdmgroup.dottracer.service.ParcelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/parcels")
public class ParcelController {

	private ParcelService parcelService;

	public ParcelController(ParcelService parcelService) {
		this.parcelService = parcelService;
	}

	@GetMapping("/number/{parcelNumber}")
	public ResponseEntity<?> findByParcelNumber(@PathVariable String parcelNumber) {
		Optional<Parcel> parcel = this.parcelService.findByParcelNumber(parcelNumber);

		if (parcel.isEmpty()) {
			return new ResponseEntity<>("The parcel doesn't exist", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(parcel.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addParcel(@Valid @RequestBody Parcel parcel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();

			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.parcelService.addParcel(parcel), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(this.parcelService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Parcel> parcel = this.parcelService.findById(id);

		if (parcel.isEmpty()) {
			return new ResponseEntity<>("The parcel doesn't exist", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(parcel.get(), HttpStatus.OK);
	}

	@GetMapping("/parcels/{senderId}")
	public ResponseEntity<?> findBySenderId(@PathVariable("senderId") String senderId) {
		List<Parcel> parcels = this.parcelService.findAllBySenderId(senderId);

		return new ResponseEntity<>(parcels, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updateParcel(@Valid @RequestBody Parcel parcel) {
		return new ResponseEntity<>(this.parcelService.updateParcel(parcel), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeParcelById(@PathVariable Long id) {
		Optional<Parcel> parcel = this.parcelService.findById(id);
		if (parcel.isEmpty()) {
			return new ResponseEntity<>("The parcel doesn't exist", HttpStatus.NOT_FOUND);
		}
		this.parcelService.removeParcelById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
