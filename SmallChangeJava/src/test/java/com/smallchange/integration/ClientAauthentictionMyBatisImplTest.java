package com.smallchange.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.LoginClientDetails;
import com.smallchange.uimodel.RegistrationClientDetails;

@SpringBootTest
@Transactional
class ClientAauthentictionMyBatisImplTest {
	
	@Autowired
	private ClientAauthentictionMyBatisImpl dao;
	
	
	@DisplayName("should check client login")
	@Test
	void testClientLogin() {
		LoginClientDetails login=new LoginClientDetails("teamloki@gmail.com","teamloki");
		ClientSendBackDetails clientSendBackDetails=dao.loginAuthenticationService(login);
		System.out.println(clientSendBackDetails.getClientId());
		assertEquals("1", clientSendBackDetails.getClientId());
	}
	
	@DisplayName("should check client login invalid")
	@Test
	void testClientLogin1() {
		LoginClientDetails login=new LoginClientDetails("teamloki1@gmail.com","teamloki");
		assertNull(dao.loginAuthenticationService(login));
	}
	
	@DisplayName("should check the registration")
	@Test
	void testClientRegistration() {
		RegistrationClientDetails client=new RegistrationClientDetails("aa@gmail.com","aa1234","aaa1234");
		ClientSendBackDetails register=dao.registrationAuthenticationService(client);
		System.out.println(register.getMessage());
		assertEquals(register.getMessage(),"Success");
	}

}
