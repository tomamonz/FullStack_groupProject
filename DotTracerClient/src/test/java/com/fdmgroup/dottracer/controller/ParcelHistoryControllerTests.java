package com.fdmgroup.dottracer.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.dottracer.model.ParcelHistory;
import com.fdmgroup.dottracer.service.ParcelHistoryServiceImp;

@WebMvcTest(ParcelHistoryController.class)
class ParcelHistoryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParcelHistoryServiceImp parcelHistoryService;

	@Test
	void addParcelHistory_withValidParcelHistory_returnsCreatedStatus() throws Exception {
		// Arrange
		ParcelHistory parcelHistory = new ParcelHistory();
		parcelHistory.setLocation("Some Location");
		// Set other properties of parcelHistory as needed

		given(parcelHistoryService.addParcelHistory(any(ParcelHistory.class))).willReturn(parcelHistory);

		// Act
		MvcResult result = mockMvc.perform(post("/api/v1/parcelhistory").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(parcelHistory))).andExpect(status().isCreated()).andReturn();

		// Assert
		verify(parcelHistoryService, times(1)).addParcelHistory(any(ParcelHistory.class));
		// Perform additional assertions as needed
	}

	@Test
	void addParcelHistory_withInvalidParcelHistory_returnsBadRequest() throws Exception {
		// Arrange
		ParcelHistory parcelHistory = new ParcelHistory();
		// Set invalid or missing properties of parcelHistory as needed

		// Act
		MvcResult result = mockMvc.perform(post("/api/v1/parcelhistory").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(parcelHistory))).andExpect(status().isBadRequest()).andReturn();

		// Assert
		// Perform assertions to check the response body or specific error messages
	}

	// Utility method to convert objects to JSON string
	private String asJsonString(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}
}
