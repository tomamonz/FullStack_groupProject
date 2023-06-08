package com.fdmgroup.dottracer.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fdmgroup.dottracer.model.Role;
import com.fdmgroup.dottracer.model.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	public void setup() {
		user = User.builder().password("abc1234").email("jd@msn.com").role(Role.SENDER).build();
	}

	@Test
	@DisplayName("Find Employee By email and password")
	void arrangeUser_actFindByEmailAndPassword_assertValidateReturnedEmployee() {
		// arrange
		userRepository.save(user);

		// act
		Optional<User> actual = userRepository.findByEmailAndPassword("jd@msn.com", "abc1234");

		// assert
		assertThat(actual.get().getPassword()).isEqualTo("abc1234");
		assertThat(actual).isEqualTo(Optional.ofNullable(user));
	}

	@Test
	@DisplayName("Find Employee By email")
	void arrangeUser_actFindByEmail_assertValidateReturnedEmployee() {
		// arrange
		userRepository.save(user);

		// act
		Optional<User> actual = userRepository.findByEmail("jd@msn.com");

		// assert
		assertThat(actual.get().getPassword()).isEqualTo("abc1234");
		assertThat(actual).isEqualTo(Optional.ofNullable(user));
	}

	@AfterEach
	public void tearDown() {
		userRepository.deleteAll();
		user = null;
	}
}
