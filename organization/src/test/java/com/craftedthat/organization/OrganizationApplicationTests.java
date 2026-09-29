package com.craftedthat.organization;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import com.craftedthat.organization.models.RegistrationModel;
import com.craftedthat.organization.services.RegistrationService;

@SpringBootTest
class OrganizationApplicationTests {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private RegistrationService registrationService;

	@Test
	void contextLoads() {
	}

	@Test
	void registeredAndAdminUsersCanSignIn() throws Exception {
		RegistrationModel user = new RegistrationModel();
		user.setUsername("signin-test-user");
		user.setPassword("test-password");
		registrationService.register(user);

		AuthenticationManager authenticationManager =
				authenticationConfiguration.getAuthenticationManager();
		var authentication = authenticationManager.authenticate(
				UsernamePasswordAuthenticationToken.unauthenticated(
						"signin-test-user", "test-password"));
		var adminAuthentication = authenticationManager.authenticate(
				UsernamePasswordAuthenticationToken.unauthenticated(
						"admin", "admin123!"));

		assertThat(authentication.isAuthenticated()).isTrue();
		assertThat(adminAuthentication.isAuthenticated()).isTrue();
	}

}
