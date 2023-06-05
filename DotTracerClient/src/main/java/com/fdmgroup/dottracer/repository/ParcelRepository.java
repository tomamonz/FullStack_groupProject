package com.fdmgroup.dottracer.repository;

import org.springframework.data.repository.CrudRepository;

import com.fdmgroup.dottracer.model.Parcel;

public interface ParcelRepository extends CrudRepository<Parcel, Long> {

	Optional<Parcel> findByParcelNumber(Long parcelNumber);
}
