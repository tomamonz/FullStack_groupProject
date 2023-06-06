package com.fdmgroup.dottracer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.dottracer.model.Parcel;

public interface ParcelRepository extends CrudRepository<Parcel, Long> {

	public Optional<Parcel> findByParcelNumber(@Param("parcelNumber") String parcelNumber);
}
