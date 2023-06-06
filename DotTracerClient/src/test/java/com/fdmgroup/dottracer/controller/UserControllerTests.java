package com.fdmgroup.dottracer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.dottracer.model.Role;
import com.fdmgroup.dottracer.model.User;
import com.fdmgroup.dottracer.service.UserServiceImp;

@WebMvcTest
class UserControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	UserServiceImp mockUserServiceImp;

	private User user;

	@BeforeEach
	public void setup() {
		user = User.builder().password("abc1234").email("jd@msn.com").role(Role.SENDER).build();
	}

	@Test
	@DisplayName("Add User")
	void arrangeUser_actAddUser_assertReturnAddedUser() throws Exception {
		// arrange
		BDDMockito.given(mockUserServiceImp.addUser(ArgumentMatchers.any(User.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// act
		ResultActions response = mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));

		// assert
		/// @formatter:off
		response.andExpect(status()
				.isCreated())
				.andExpect(jsonPath("$.password", is("abc1234")))
				.andExpect(jsonPath("$.email", is("jd@msn.com")));
		// @formatter:on
		verify(mockUserServiceImp).addUser(ArgumentMatchers.any(User.class));
	}

	@Test
	@DisplayName("Add invalid User")
	void arrangeInvalidUser_actAddUser_assertReturnNull() throws Exception {
		// arrange
		user = User.builder().password("").build();

		// act
		ResultActions response = mockMvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));

		// assert
		response.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("Find User by email and password")
	void arrangeUser_actFindUserByEmailAndPassword_assertReturnEmployeeWithGivenEmailAndPassword() throws Exception {
		// arrange
		BDDMockito.given(mockUserServiceImp.findByEmailAndPassword("jd@msn.com", "abc1234"))
				.willReturn(Optional.ofNullable(user));

		// act
		ResultActions response = mockMvc.perform(get("/api/v1/users/{email}/{password}", "jd@msn.com", "abc1234"));

		// assert
		/// @formatter:off
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.email", is("jd@msn.com")))
				.andExpect(jsonPath("$.password", is("abc1234")));
		// @formatter:on
		verify(mockUserServiceImp).findByEmailAndPassword("jd@msn.com", "abc1234");
	}

	@Test
	@DisplayName("Find User with invalid email and password")
	void arrangeInvalidUser_actFindUserByEmailAndPassword_assertReturnNull() throws Exception {
		// arrange
		BDDMockito.given(mockUserServiceImp.findByEmailAndPassword("sth@msn.com", "123456"))
				.willReturn(Optional.empty());

		// act
		ResultActions response = mockMvc.perform(get("/api/v1/users/{email}/{password}", "sth@msn.com", "123456"));

		// assert
		/// @formatter:off
		response.andExpect(status().isNotFound());
				
		// @formatter:on
		verify(mockUserServiceImp).findByEmailAndPassword("sth@msn.com", "123456");
	}

}
