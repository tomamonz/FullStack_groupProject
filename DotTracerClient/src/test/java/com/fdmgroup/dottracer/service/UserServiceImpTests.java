package com.fdmgroup.dottracer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.dottracer.model.Role;
import com.fdmgroup.dottracer.model.User;
import com.fdmgroup.dottracer.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTests {
	@InjectMocks
	private UserServiceImp userServiceImp;

	@Mock
	UserRepository mockUserRepository;

	private User user;

	@BeforeEach
	public void setup() {
		user = new User();
		user.setEmail("jd@msn.com");
		user.setPassword("abc1234");
		user.setRole(Role.SENDER);
	}

	@Test
	@DisplayName("Add User")
	void arrangeUser_actAddUser_assertReturnSavedUser() {
		//arrange
		when(mockUserRepository.save(user)).thenReturn(user);
		
		//act
		User actual = userServiceImp.addUser(user);
		
		//assert
		assertThat(actual).isEqualTo(user);
		verify(mockUserRepository).save(user);
	}

	@Test
	@DisplayName("Find User by email and password")
	void arrangeUser_actFindByEmailAndPassword_assertUserWithGivenEmailAndPassword() {
		//arrange
		when(mockUserRepository.findByEmailAndPassword("jd@msn.com", "abc1234")).thenReturn(Optional.ofNullable(user));
		
		//act
		Optional<User> actual = userServiceImp.findByEmailAndPassword("jd@msn.com", "abc1234");
		
		//assert
		
	}

}
