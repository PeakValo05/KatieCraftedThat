package com.craftedthat.organization;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class OrganizationApplicationTests {

	@Autowired
	private MockMvc mockMvc;

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
