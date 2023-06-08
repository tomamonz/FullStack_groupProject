package com.fdmgroup.dottracer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

	@Test
	void arrangeHistories_ActFindAllByParcelNumber_assertReturnsParcelHistories() {

		// Arrange
		ParcelHistory parcelHistory = new ParcelHistory();
		ParcelHistory parcelHistory2 = new ParcelHistory();
		parcelHistory.setParcelNumber("123");
		parcelHistory2.setParcelNumber("123");

		List<ParcelHistory> history = List.of(parcelHistory, parcelHistory2);
		when(mockParcelHistoryRepository.findAllByParcelNumber("123")).thenReturn(history);

		// Act
		List<ParcelHistory> actual = new ArrayList<>();
		parcelHistoryServiceImp.findAllByParcelNumber("123").forEach(actual::add);

		// Assert
		assertThat(actual).isEqualTo(history);
		verify(mockParcelHistoryRepository, times(1)).findAllByParcelNumber("123");
	}

}
