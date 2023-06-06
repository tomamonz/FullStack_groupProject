package com.fdmgroup.dottracer.service;

import org.springframework.stereotype.Service;

import com.fdmgroup.dottracer.model.ParcelHistory;
import com.fdmgroup.dottracer.repository.ParcelHistoryRepository;

@Service
public class ParcelHistoryServiceImp implements ParcelHistoryService {

	private ParcelHistoryRepository parcelHistoryRepository;

	public ParcelHistoryServiceImp(ParcelHistoryRepository parcelHistoryRepository) {
		this.parcelHistoryRepository = parcelHistoryRepository;
	}

	@Override
	public ParcelHistory addParcelHistory(ParcelHistory parcelHistory) {
		return parcelHistoryRepository.save(parcelHistory);
	}

}
