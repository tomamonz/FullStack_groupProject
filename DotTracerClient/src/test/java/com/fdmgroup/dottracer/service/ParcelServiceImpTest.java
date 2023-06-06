package com.fdmgroup.dottracer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fdmgroup.dottracer.model.Parcel;
import com.fdmgroup.dottracer.model.Status;
import com.fdmgroup.dottracer.repository.ParcelRepository;

@SpringBootTest
class ParcelServiceImpTest {

	private Parcel parcel;

	@InjectMocks
	private ParcelServiceImp parcelService;

	@Mock
	private ParcelRepository mockParcelRepository;

	@BeforeEach
	void setUp() throws Exception {
		parcel = Parcel.builder().parcelNumber("12345").senderId(UUID.randomUUID().toString().replaceAll("-", ""))
				.status(Status.SUBMITTED).build();
	}

	@Test
	@DisplayName("add parcel")
	void arrangeParcel_actAddParcel_assertParcelSaved() {
		
		// arrange
		when(mockParcelRepository.save(parcel)).thenReturn(parcel);
		
		// act
		Parcel actual = parcelService.addParcel(parcel);
		
		// assert
		assertThat(actual).isEqualTo(parcel);
		verify(mockParcelRepository, times(1)).save(parcel);
	}

	@Test
	@DisplayName("find by parcel number")
	void arrangeParcel_actFindByParcelNumber_assertParcelFound() {
		
		// arrange
		when(mockParcelRepository.findByParcelNumber("12345")).thenReturn(Optional.of(parcel));
		
		// act
		Parcel actual = parcelService.findByParcelNumber("12345").get();
		
		// assert
		assertThat(actual).isEqualTo(parcel);
		verify(mockParcelRepository, times(1)).findByParcelNumber("12345");
	}

	@Test
	@DisplayName("find by parcel id")
	void arrangeParcel_actFindByParcelId_assertParcelFound() {

		// arrange
		Long id = parcel.getId();
		when(mockParcelRepository.findById(id)).thenReturn(Optional.of(parcel));

		// act
		Parcel actual = parcelService.findById(id).get();

		// assert
		assertThat(actual).isEqualTo(parcel);
		verify(mockParcelRepository, times(1)).findById(id);
	}

	@Test
	@DisplayName("find all")
	void arrangeParcels_actFindAll_assertParcelsFound() {

		// arrange
		Parcel parcel2 = Parcel.builder().parcelNumber("12345")
				.senderId(UUID.randomUUID().toString().replaceAll("-", "")).status(Status.SUBMITTED).build();
		List<Parcel> parcels = List.of(parcel, parcel2);
		when(mockParcelRepository.findAll()).thenReturn(parcels);

		// act
		List<Parcel> actual = new ArrayList<>();
		parcelService.findAll().forEach(actual::add);

		// assert
		assertThat(actual).isEqualTo(parcels);
		verify(mockParcelRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("update parcel")
	void arrangeParcel_actUpdateParcel_assertParcelSaved() {
		
		//arrange
		when(mockParcelRepository.save(parcel)).thenReturn(parcel);

		//act
		Parcel actual = parcelService.updateParcel(parcel);

		//assert
		assertThat(actual).isEqualTo(parcel);
		verify(mockParcelRepository, times(1)).save(parcel);
	}

	@Test
	@DisplayName("remove parcel by id")
	void actDeleteParcel_assertDeleteCalled() {

		// act
		parcelService.removeParcelById(12345L);

		// assert
		verify(mockParcelRepository, times(1)).deleteById(12345L);
	}

}
