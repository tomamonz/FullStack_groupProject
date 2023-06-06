package com.fdmgroup.dottracer.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.dottracer.model.ParcelHistory;
import com.fdmgroup.dottracer.repository.ParcelHistoryRepository;

@ExtendWith(MockitoExtension.class)
class ParcelHistoryServiceImpTests {

	@Mock
	private ParcelHistoryRepository mockParcelHistoryRepository;

	@InjectMocks
	private ParcelHistoryServiceImp parcelHistoryServiceImp;

	@Test
	void addParcelHistory_ValidParcelHistory_CallsRepositorySave() {
		// Arrange
		ParcelHistory parcelHistory = new ParcelHistory();

		// Act
		parcelHistoryServiceImp.addParcelHistory(parcelHistory);

		// Assert
		verify(mockParcelHistoryRepository).save(ArgumentMatchers.any(ParcelHistory.class));
	}
}
