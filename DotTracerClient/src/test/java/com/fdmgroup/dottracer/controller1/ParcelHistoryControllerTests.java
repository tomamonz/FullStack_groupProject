package com.fdmgroup.dottracer.controller1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.dottracer.model.ParcelHistory;
import com.fdmgroup.dottracer.service.ParcelHistoryServiceImp;

@WebMvcTest
class ParcelHistoryControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	ParcelHistoryServiceImp mockParcelHistoryService;

	private ParcelHistory parcelHistory;

	@BeforeEach
	public void setup() {
		parcelHistory = ParcelHistory.builder().location("Krakow").build();
	}

	@Test
	@DisplayName("Add ParcelHistory")
	void arrangeParcelHistory_actAddParcelHistory_assertReturnAddedParcelHistory() throws Exception {
		// arrange
		BDDMockito.given(mockParcelHistoryService.addParcelHistory(ArgumentMatchers.any(ParcelHistory.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));

		// act
		ResultActions response = mockMvc.perform(post("/api/v1/parcelhistory").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parcelHistory)));

		// assert
		/// @formatter:off
		response.andExpect(status()
				.isCreated())
				.andExpect(jsonPath("$.location", is("Krakow")));
		// @formatter:on
		verify(mockParcelHistoryService).addParcelHistory(ArgumentMatchers.any(ParcelHistory.class));
	}

	@Test
	@DisplayName("add parcel with missing data")
	void arrangeParcelHistoryWithMissingData_actAddParcelHistory_assertReturnNull() throws Exception {

		// arrange
		parcelHistory = ParcelHistory.builder().location("").build();

		// act
		ResultActions response = mockMvc.perform(post("/api/v1/parcelhistory").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parcelHistory)));

		// assert

	/// @formatter:off
	response.andExpect(status().isBadRequest());
	// @formatter:on
	}

	@Test
	void addParcelHistory_WithValidData_ReturnsCreated() throws Exception {
		// Arrange
		ParcelHistory parcelHistory = new ParcelHistory();
		parcelHistory.setLocation("Sample Location");

		BDDMockito.given(mockParcelHistoryService.addParcelHistory(ArgumentMatchers.any(ParcelHistory.class)))
				.willReturn(parcelHistory);

		String jsonPayload = "{\"location\": \"Sample Location\"}";

		// Act
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/parcelhistory")
				.contentType(MediaType.APPLICATION_JSON).content(jsonPayload));

		// Assert
		response.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void arrangeParcelHistoryList_actGetAllByParcelNumber_assertReturnParcelHistoryList() throws Exception {

		// arrange
		ParcelHistory parcelHistory = new ParcelHistory();
		parcelHistory.setParcelNumber("12345");
		ParcelHistory parcelHistory2 = new ParcelHistory();
		parcelHistory.setParcelNumber("12345");

		List<ParcelHistory> histories = List.of(parcelHistory, parcelHistory2);
		BDDMockito.given(mockParcelHistoryService.findAllByParcelNumber("12345")).willReturn(histories);
		// act
		ResultActions response = mockMvc.perform(get("/api/v1/parcelhistory/12345"));

		// assert
		response.andExpect(status().isOk()).andExpect(jsonPath("$.*", isA(ArrayList.class)))
				.andExpect(jsonPath("$.*", hasSize(2))).andReturn();

	}
}
