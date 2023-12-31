package com.fdmgroup.dottracer.service;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.dottracer.model.Parcel;

public interface ParcelService {
	public Optional<Parcel> findByParcelNumber(String parcelNumber);

	public Parcel addParcel(Parcel parcel);

	public Iterable<Parcel> findAll();

	public Optional<Parcel> findById(Long id);

	public Parcel updateParcel(Parcel parcel);

	public void removeParcelById(Long id);

	public List<Parcel> findAllBySenderId(String senderId);
}
