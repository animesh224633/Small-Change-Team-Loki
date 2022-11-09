package com.smallchange.restService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.LoginClientDetails;
import com.smallchange.uimodel.RegistrationClientDetails;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"}) 
class ClientAuthenticationServiceE2ETest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testClientLogin() {
		LoginClientDetails client=new LoginClientDetails();
		client.setClientMail("teamloki@gmail.com");
		client.setPassword("teamloki");
		HttpEntity<LoginClientDetails> requestEntity = new HttpEntity<>(client);
		String request = "/clientAuthentication/login";
		ResponseEntity<ClientSendBackDetails> response=restTemplate.postForEntity(request, requestEntity, ClientSendBackDetails.class);
		System.out.println(response.getBody().getClientId());
		assertEquals(response.getBody().getClientId(),"1");
	}
	
	@Test
	void testClientLoginInvalid() {
		LoginClientDetails client=new LoginClientDetails();
		client.setClientMail("teamloki2@gmail.com");
		client.setPassword("teamloki");
		HttpEntity<LoginClientDetails> requestEntity = new HttpEntity<>(client);
		String request = "/clientAuthentication/login";
		ResponseEntity<ClientSendBackDetails> response=restTemplate.postForEntity(request, requestEntity, ClientSendBackDetails.class);
		System.out.println(response.getBody().getClientId());
		assertNull(response.getBody().getClientId());
	}
	
	@Test
	void testClientRegistration() {
		RegistrationClientDetails registrationClientDetails=new RegistrationClientDetails();
		registrationClientDetails.setClientMail("seneha@gmail.com");
		registrationClientDetails.setClientName("seneha");
		registrationClientDetails.setPassword("seneha1561");
		HttpEntity<RegistrationClientDetails> requestEntity = new HttpEntity<>(registrationClientDetails);
		String request = "/clientAuthentication/registration";
		ResponseEntity<ClientSendBackDetails> response=restTemplate.postForEntity(request, requestEntity, ClientSendBackDetails.class);
		System.out.println(response.getBody().getMessage());
		assertEquals(response.getBody().getMessage(),"Success");
	}

	@Test
	void testClientRegistrationInvalid() {
		RegistrationClientDetails registrationClientDetails=new RegistrationClientDetails();
		registrationClientDetails.setClientMail("teamloki@gmail.com");
		registrationClientDetails.setClientName("seneha");
		registrationClientDetails.setPassword("teamloki");
		HttpEntity<RegistrationClientDetails> requestEntity = new HttpEntity<>(registrationClientDetails);
		String request = "/clientAuthentication/registration";
		ResponseEntity<ClientSendBackDetails> response=restTemplate.postForEntity(request, requestEntity, ClientSendBackDetails.class);
		System.out.println(response.getBody().getMessage());
		assertEquals(response.getBody().getMessage(),"Already Present");
	}

}
