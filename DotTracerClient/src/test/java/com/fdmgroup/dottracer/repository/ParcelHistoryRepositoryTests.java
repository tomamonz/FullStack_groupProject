package com.fdmgroup.dottracer.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdmgroup.dottracer.model.ParcelHistory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ParcelHistoryRepositoryTests {

	@Autowired
	private ParcelHistoryRepository parcelHistoryRepository;

	private ParcelHistory parcelHistory;

	@BeforeEach
	void setUp() throws Exception {
		parcelHistory = ParcelHistory.builder().build();
	}

	@Test
	@DisplayName("find parcel by number")
	void arrangeSaveParcel_actFindByNumber_assertParcelFound() {

		// arrange
		parcelHistory.setLocation("Krakow");
		ParcelHistory original = parcelHistoryRepository.save(parcelHistory);

		// act
		Iterable<ParcelHistory> actual = parcelHistoryRepository.findAllByParcelNumber(original.getParcelNumber());

		// assert
		assertThat(actual).contains(original);

	}

}
