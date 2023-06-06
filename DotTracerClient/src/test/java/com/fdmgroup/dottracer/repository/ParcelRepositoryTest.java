package com.fdmgroup.dottracer.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdmgroup.dottracer.model.Parcel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ParcelRepositoryTest {

	@Autowired
	private ParcelRepository repo;

	private Parcel parcel;

	@BeforeEach
	void setUp() throws Exception {
		parcel = Parcel.builder().build();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("find all parcels")
	void arrangeParcel_actFindAll_assertCheckParcelInDatabase() {

		// arrange
		// Parcel parcel = new Parcel(1L, "adasdad", 1L, Status.SUBMITTED, null);
		Parcel parcel2 = new Parcel();
		List<Parcel> books = List.of(parcel, parcel2);
		repo.saveAll(books);

		// act
		List<Parcel> actual = (List<Parcel>) repo.findAll();

		// assert
		assertThat(repo.count()).isEqualTo(2);
	}

	@AfterEach
	void tearDown() {
		repo.deleteAll();
	}

}
