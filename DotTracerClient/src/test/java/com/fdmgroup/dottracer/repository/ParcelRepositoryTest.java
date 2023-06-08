package com.fdmgroup.dottracer.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdmgroup.dottracer.model.Parcel;
import com.fdmgroup.dottracer.model.Status;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ParcelRepositoryTest {

	@Autowired
	private ParcelRepository repo;

	private Parcel parcel;

	@BeforeEach
	void setUp() throws Exception {
		parcel = Parcel.builder().senderId("12345").status(Status.SUBMITTED).build();

	}

	@Test
	@DisplayName("save parcel")
	void arrangeParcel_actSave_assertCheckParcelInDatabase() {

		// arrange

		// act
		Parcel actual = repo.save(parcel);

		// assert
		assertThat(repo.count()).isEqualTo(1);
		assertThat(repo.findById(actual.getId()).get().getSenderId()).isEqualTo(parcel.getSenderId());
	}

	@Test
	@DisplayName("find all parcels")
	void arrangeParcel_actFindAll_assertCheckParcelInDatabase() {

		// arrange
		Parcel parcel2 = Parcel.builder().senderId("23456").status(Status.SUBMITTED).build();

		List<Parcel> parcels = List.of(parcel, parcel2);
		repo.saveAll(parcels);

		// act
		List<Parcel> actual = (List<Parcel>) repo.findAll();

		// assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0).getSenderId()).isEqualTo(parcel.getSenderId());
		assertThat(actual.get(1).getSenderId()).isEqualTo(parcel2.getSenderId());

	}

	@Test
	@DisplayName("find parcel by number")
	void arrangeSaveParcel_actFindByNumber_assertParcelFound() {

		// arrange
		Parcel original = repo.save(parcel);

		// act
		Optional<Parcel> actual = repo.findByParcelNumber(original.getParcelNumber());

		// assert
		assertThat(actual.get().getSenderId()).isEqualTo(parcel.getSenderId());

	}

	@Test
	@DisplayName("find all parcels by senderId")
	void arrangeSaveParcels_actFindAllBySenderId_assertParcelsReturned() {

		// arrange
		Parcel parcel2 = Parcel.builder().senderId("12345").status(Status.SUBMITTED).build();
		Parcel parcel3 = Parcel.builder().senderId("23456").status(Status.SUBMITTED).build();
		List<Parcel> parcels = List.of(parcel, parcel2, parcel3);
		repo.saveAll(parcels);

		// act
		List<Parcel> actual = repo.findAllBySenderId("12345");

		// assert
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0).getSenderId()).isEqualTo("12345");
		assertThat(actual.get(1).getSenderId()).isEqualTo("12345");

	}

	@AfterEach
	void tearDown() {
		repo.deleteAll();
	}

}
