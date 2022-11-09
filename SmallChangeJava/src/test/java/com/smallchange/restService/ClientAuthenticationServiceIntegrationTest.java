package com.smallchange.restService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.LoginClientDetails;
import com.smallchange.uimodel.RegistrationClientDetails;

@SpringBootTest
@Transactional
class ClientAuthenticationServiceIntegrationTest {

	@Autowired
	private ClientAuthenticationService service;
	
	
	@Test
	void testClientLogin() {
		LoginClientDetails client=new LoginClientDetails();
		client.setClientMail("teamloki@gmail.com");
		client.setPassword("teamloki");
		ClientSendBackDetails clientDetails=service.getClientIdFromLoginCredentials(client);
		assertEquals(clientDetails.getClientId(),"1");
	}
	
	@Test
	void testClientLoginInvalid() {
		LoginClientDetails client=new LoginClientDetails();
		client.setClientMail("teamloki2@gmail.com");
		client.setPassword("teamloki");
		ClientSendBackDetails clientDetails=service.getClientIdFromLoginCredentials(client);
		assertNull(clientDetails.getClientId());
	}
	
	@Test
	void testClientRegistration() {
		RegistrationClientDetails registrationClientDetails=new RegistrationClientDetails();
		registrationClientDetails.setClientMail("seneha@gmail.com");
		registrationClientDetails.setClientName("seneha");
		registrationClientDetails.setPassword("seneha1561");
		ClientSendBackDetails clientDetails=service.queryTradeHistoryByClientId(registrationClientDetails);
		assertEquals(clientDetails.getMessage(),"Success");
	}
	
	@Test
	void testClientRegistrationInvalid() {
		RegistrationClientDetails registrationClientDetails=new RegistrationClientDetails();
		registrationClientDetails.setClientMail("teamloki@gmail.com");
		registrationClientDetails.setClientName("seneha");
		registrationClientDetails.setPassword("teamloki");
		ClientSendBackDetails clientDetails=service.queryTradeHistoryByClientId(registrationClientDetails);
		assertEquals(clientDetails.getMessage(),"Already Present");
	}

}
