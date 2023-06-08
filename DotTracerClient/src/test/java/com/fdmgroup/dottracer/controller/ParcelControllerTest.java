package com.fdmgroup.dottracer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import com.fdmgroup.dottracer.model.Parcel;
import com.fdmgroup.dottracer.model.Status;
import com.fdmgroup.dottracer.service.ParcelServiceImp;

@WebMvcTest
class ParcelControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	ParcelServiceImp mockParcelService;

	private Parcel parcel;

	@BeforeEach
	void setUp() throws Exception {
		parcel = Parcel.builder().id(123L).parcelNumber("123456").senderId("abc123").status(Status.SUBMITTED).build();
	}

	@Test
	@DisplayName("add parcel")
	void arrangeParcel_actAddParcel_assertReturnSavedParcel() throws Exception {

		// arrange
		BDDMockito.given(mockParcelService.addParcel(ArgumentMatchers.any(Parcel.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// act
		ResultActions response = mockMvc.perform(post("/api/v1/parcels").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parcel)));

		// assert

		/// @formatter:off
		response.andExpect(status()
				.isCreated())
				.andExpect(jsonPath("$.senderId", is("abc123")));
		// @formatter:on

		verify(mockParcelService).addParcel(ArgumentMatchers.any(Parcel.class));
	}

	@Test
	@DisplayName("add parcel with missing data")
	void arrangeParcelWithMissingData_actAddParcel_assertReturnNull() throws Exception {

		// arrange
		parcel = Parcel.builder().senderId("").build();

		// act
		ResultActions response = mockMvc.perform(post("/api/v1/parcels").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parcel)));

		// assert

		/// @formatter:off
		response.andExpect(status().isBadRequest());
		// @formatter:on
	}

	@Test
	@DisplayName("find all parcels")
	void arrangeParcelList_actGetAllParcels_assertReturnParcelList() throws Exception {

		// arrange
		Parcel parcel2 = Parcel.builder().parcelNumber(UUID.randomUUID().toString().replaceAll("-", ""))
				.senderId("23456").status(Status.SUBMITTED).build();

		List<Parcel> parcels = List.of(parcel, parcel2);
		BDDMockito.given(mockParcelService.findAll()).willReturn(parcels);

		// act
		ResultActions response = mockMvc.perform(get("/api/v1/parcels"));

		// assert
		response.andExpect(status().isOk()).andExpect(jsonPath("$.*", isA(ArrayList.class)))
				.andExpect(jsonPath("$.*", hasSize(2))).andReturn();
	}

	@Test
	@DisplayName("Find by parcel number")
	void arrangeParcel_actFindByParcelNumber_assertReturnParcelWithGivenNumber() throws Exception {

		// arrange
		BDDMockito.given(mockParcelService.findByParcelNumber("123")).willReturn(Optional.ofNullable(parcel));

		// act
		ResultActions response = mockMvc.perform(get("/api/v1/parcels/number/{parcelNumber}", "123"));

		// assert

		/// @formatter:off
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.senderId", is("abc123")));
		// @formatter:on

		verify(mockParcelService).findByParcelNumber("123");
	}

	@Test
	@DisplayName("Find by parcel number that doesnt exist")
	void arrangeParcel_actFindByInvalidParcelNumber_assertReturnEmptyOptional() throws Exception {

		// arrange
		BDDMockito.given(mockParcelService.findByParcelNumber("123")).willReturn(Optional.empty());

		// act
		ResultActions response = mockMvc.perform(get("/api/v1/parcels/number/{parcelNumber}", "123"));

		// assert

		/// @formatter:off
		response.andExpect(status().isNotFound());
		// @formatter:on

		verify(mockParcelService).findByParcelNumber("123");
	}

	@Test
	@DisplayName("Find by parcel id")
	void arrangeParcel_actFindByParcelId_assertReturnParcelWithGivenId() throws Exception {

		// arrange
		BDDMockito.given(mockParcelService.findById(123L)).willReturn(Optional.ofNullable(parcel));

		// act
		ResultActions response = mockMvc.perform(get("/api/v1/parcels/{id}", "123"));

		// assert

		/// @formatter:off
		response.andExpect(status().isOk())
				.andExpect(jsonPath("$.senderId", is("abc123")));
		// @formatter:on

		verify(mockParcelService).findById(123L);
	}

	@Test
	@DisplayName("Find by parcel id that doesnt exist")
	void arrangeParcel_actFindByInvalidParcelId_assertReturnEmptyOptional() throws Exception {

		// arrange
		BDDMockito.given(mockParcelService.findById(123L)).willReturn(Optional.empty());

		// act
		ResultActions response = mockMvc.perform(get("/api/v1/parcels/{id}", 123L));

		// assert

		/// @formatter:off
		response.andExpect(status().isNotFound());
		// @formatter:on

		verify(mockParcelService).findById(123L);
	}

	@Test
	@DisplayName("update parcel")
	void arrangeParcel_actUpdateParcel_assertReturnUpdatedParcel() throws Exception {

		// arrange
		BDDMockito.given(mockParcelService.findById(parcel.getId())).willReturn(Optional.of(parcel));
		BDDMockito.given(mockParcelService.updateParcel(ArgumentMatchers.any(Parcel.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// act
		ResultActions response = mockMvc.perform(put("/api/v1/parcels").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parcel)));

		// assert

		/// @formatter:off
		response.andExpect(status()
				.isOk());
				//.andExpect(jsonPath("$.senderId", is("abc123")));
		// @formatter:on

		verify(mockParcelService).updateParcel(ArgumentMatchers.any(Parcel.class));
	}

	@Test
	@DisplayName("remove parcel by id")
	void arrangeId_actRemoveParcelById_assertReturnStatusOK() throws Exception {

		// arrange
		long id = 123;
		BDDMockito.given(mockParcelService.findById(123L)).willReturn(Optional.of(parcel));
		BDDMockito.willDoNothing().given(mockParcelService).removeParcelById(id);

		// act
		ResultActions response = mockMvc.perform(delete("/api/v1/parcels/{id}", id));

		// assert
		response.andExpect(status().isOk());
	}

	@Test
	@DisplayName("remove parcel by invalid id")
	void arrangeInvalidId_actRemoveParcelById_assertReturnStatusNotFound() throws Exception {

		// arrange
		long id = 123;
		BDDMockito.given(mockParcelService.findById(123L)).willReturn(Optional.empty());
		BDDMockito.willDoNothing().given(mockParcelService).removeParcelById(id);

		// act
		ResultActions response = mockMvc.perform(delete("/api/v1/parcels/{id}", id));

		// assert
		response.andExpect(status().isNotFound());
	}

}
