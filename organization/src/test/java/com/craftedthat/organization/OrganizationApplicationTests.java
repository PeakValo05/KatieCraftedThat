package com.craftedthat.organization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// Verify the expected public and restricted HTTP routes.
	@Test
	void storefrontIsPublic() throws Exception {
		mockMvc.perform(get("/home")).andExpect(status().isOk());
	}

	@Test
	void loginPageIsNotAvailable() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isNotFound());
	}

	@Test
	void adminDashboardStaysBlocked() throws Exception {
		mockMvc.perform(get("/admin/subscribers")).andExpect(status().isForbidden());
	}
}
