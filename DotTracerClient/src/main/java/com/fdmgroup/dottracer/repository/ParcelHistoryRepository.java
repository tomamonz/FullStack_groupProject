package com.fdmgroup.dottracer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.dottracer.model.ParcelHistory;

public interface ParcelHistoryRepository extends CrudRepository<ParcelHistory, Long> {

    public Iterable<ParcelHistory> findAllByParcelNumber(@Param("parcelNumber") String parcelNumber);

}
