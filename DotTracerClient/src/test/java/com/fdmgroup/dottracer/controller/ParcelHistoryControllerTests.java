import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fdmgroup.dottracer.model.ParcelHistory;
import com.fdmgroup.dottracer.service.ParcelHistoryServiceImp;

@WebMvcTest(ParcelHistoryController.class)
class ParcelHistoryControllerTests {

	@Autowired
	private MockMvc mvc;

	@Mock
	private ParcelHistoryServiceImp mockParcelHistoryService;

	@Test
	void addParcelHistory_WithValidData_ReturnsCreated() throws Exception {
		// Arrange
		ParcelHistory parcelHistory = new ParcelHistory();
		parcelHistory.setLocation("Sample Location");

		BDDMockito.given(mockParcelHistoryService.addParcelHistory(ArgumentMatchers.any(ParcelHistory.class)))
				.willReturn(parcelHistory);

		String jsonPayload = "{\"location\": \"Sample Location\"}";

		// Act
		ResultActions response = mvc.perform(MockMvcRequestBuilders.post("/api/v1/parcelhistory")
				.contentType(MediaType.APPLICATION_JSON).content(jsonPayload));

		// Assert
		response.andExpect(MockMvcResultMatchers.status().isCreated());
	}
}
