package com.fdmgroup.dottracer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.dottracer.model.Parcel;
import com.fdmgroup.dottracer.repository.ParcelRepository;

@Service
public class ParcelServiceImp implements ParcelService {

	private ParcelRepository parcelRepository;

	public ParcelServiceImp(ParcelRepository parcelRepository) {
		this.parcelRepository = parcelRepository;
	}

	@Override
	public Optional<Parcel> findByParcelNumber(Long parcelNumber) {
		return parcelRepository.findByParcelNumber(parcelNumber);
	}

	@Override
	public Parcel addParcel(Parcel parcel) {
		return parcelRepository.save(parcel);
	}

	@Override
	public Iterable<Parcel> findAll() {
		return parcelRepository.findAll();
	}

	@Override
	public Optional<Parcel> findById(Long id) {
		return parcelRepository.findById(id);
	}

	@Override
	public Parcel updateParcel(Parcel parcel) {
		return parcelRepository.save(parcel);
	}

	@Override
	public void removeParcelById(Long id) {
		parcelRepository.deleteById(id);
	}

}
