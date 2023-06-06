package com.fdmgroup.dottracer.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
		parcel = Parcel.builder().parcelNumber(12345L).senderId(UUID.randomUUID().toString().replaceAll("-", ""))
				.status(Status.SUBMITTED).build();
	}

	@Test
	@DisplayName("save parcel")
	void arrangeParcel_actSave_assertCheckParcelInDatabase() {
		// arrange

		// act
		Parcel actual = repo.save(parcel);

		// assert
		assertThat(repo.count()).isEqualTo(1);
		assertThat(repo.findById(actual.getId()).get().getParcelNumber()).isEqualTo(12345L);
	}

	@Test
	@DisplayName("find all parcels")
	void arrangeParcel_actFindAll_assertCheckParcelInDatabase() {

		// arrange
		Parcel parcel2 = Parcel.builder().parcelNumber(23456L)
				.senderId(UUID.randomUUID().toString().replaceAll("-", "")).status(Status.SUBMITTED).build();
		List<Parcel> parcels = List.of(parcel, parcel2);
		repo.saveAll(parcels);

		// act
		List<Parcel> actual = (List<Parcel>) repo.findAll();

		// assert
		assertThat(repo.count()).isEqualTo(2);
		assertThat(actual.get(1).getParcelNumber()).isEqualTo(23456L);

	}

	@Test
	@DisplayName("find parcel by number")
	void arrangeSaveParcel_actFindByNumber_assertParcelFound() {

		// arrange
		repo.save(parcel);

		// act
		Optional<Parcel> actual = repo.findByParcelNumber(12345L);

		// assert
		assertThat(actual.get().getParcelNumber()).isEqualTo(12345L);

	}

	@AfterEach
	void tearDown() {
		repo.deleteAll();
	}

}
