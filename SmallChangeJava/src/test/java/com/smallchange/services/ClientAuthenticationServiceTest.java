package com.smallchange.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smallchange.dao.ClientDAO;
import com.smallchange.model.ClientSendBackDetails;

class ClientAuthenticationServiceTest {
	
	ClientAuthenticationService clientAuthenticationService;
	List<ClientDAO> clientTable;
	ClientSendBackDetails clientSendBackDetailsExpected;
	ClientSendBackDetails clientSendBackDetailsActual;
	ClientDAO clientDAO1;
	ClientDAO clientDAO2;
	ClientDAO clientDAO3;
	String clientId1="", clientId2="", clientId3="";
	
	@BeforeEach
	void setup() {
		clientAuthenticationService = new ClientAuthenticationService();
		clientTable = new ArrayList<>();
		clientSendBackDetailsExpected = new ClientSendBackDetails();
		clientSendBackDetailsActual = new ClientSendBackDetails();
		clientDAO1 = new ClientDAO();
		clientId1 = UUID.randomUUID().toString();
		clientDAO1.setClientId(clientId1);
		clientDAO1.setClientMail("mail1");
		clientDAO1.setClientName("client1");
		clientDAO1.setClientSmallChangeWallet(new BigDecimal("1000.00"));
		clientDAO1.setPassword("pass1");
		clientDAO2 = new ClientDAO();
		clientId2 = UUID.randomUUID().toString();
		clientDAO2.setClientId(clientId2);
		clientDAO2.setClientMail("mail2");
		clientDAO2.setClientName("client2");
		clientDAO2.setClientSmallChangeWallet(new BigDecimal("500.00"));
		clientDAO2.setPassword("pass2");
		clientDAO3 = new ClientDAO();
		clientId3 = UUID.randomUUID().toString();
		clientDAO3.setClientId(clientId3);
		clientDAO3.setClientMail("mail3");
		clientDAO3.setClientName("client3");
		clientDAO3.setClientSmallChangeWallet(new BigDecimal("1500.00"));
		clientDAO3.setPassword("pass3");
	}
	
	@Test
	@DisplayName("Checking for scenario where clientMail and password entered are correct")
	void check1() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setClientId(clientId1);
		clientSendBackDetailsExpected.setMessage("authentication successful");
		clientSendBackDetailsActual = clientAuthenticationService.loginAuthenticationService("mail1", "pass1", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());	
		assertEquals(clientSendBackDetailsExpected.getClientId(), clientSendBackDetailsActual.getClientId());
	}
	
	@Test
	@DisplayName("Checking for scenario where clientMail is incorrect")
	void check2() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setClientId(null);
		clientSendBackDetailsExpected.setMessage("client mail or password incorrect");
		clientSendBackDetailsActual = clientAuthenticationService.loginAuthenticationService("random", "pass1", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());	
		assertEquals(clientSendBackDetailsExpected.getClientId(), clientSendBackDetailsActual.getClientId());
	}
	
	@Test
	@DisplayName("Checking for scenario where password is incorrect")
	void check3() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setClientId(null);
		clientSendBackDetailsExpected.setMessage("client mail or password incorrect");
		clientSendBackDetailsActual = clientAuthenticationService.loginAuthenticationService("mail1", "random", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());	
		assertEquals(clientSendBackDetailsExpected.getClientId(), clientSendBackDetailsActual.getClientId());
	}
	
	@Test
	@DisplayName("Checking if all details are correct for registration and cient is not already registered")
	void check4() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("User added successfully");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService("name","mailxxx", "randompassword", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}
	
	@Test
	@DisplayName("Checking if the client mail is already present")
	void check5() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("client mail already exists.");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService("name","mail1", "randompassword", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}
	
	@Test
	@DisplayName("Checking if the client name is empty while registering")
	void check6() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("Name cannot be empty or null");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService("  ","mailxxx", "randompassword", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}
	
	@Test
	@DisplayName("Checking if the client name is null while registering")
	void check7() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("Name cannot be empty or null");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService(null,"mailxxx", "randompassword", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}
	
	@Test
	@DisplayName("Checking if the client mail is null while registering")
	void check8() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("Mail cannot be empty or null");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService("abcd",null, "randompassword", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}
	
	@Test
	@DisplayName("Checking if the client mail is empty while registering")
	void check9() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("Mail cannot be empty or null");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService("abcd"," ", "randompassword", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}
	
	@Test
	@DisplayName("Checking if the client password is empty while registering")
	void check10() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("Password cannot be empty or null");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService("abcd","mail12345", "  ", clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}
	
	@Test
	@DisplayName("Checking if the client password is null while registering")
	void check11() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientSendBackDetailsExpected.setMessage("Password cannot be empty or null");
		clientSendBackDetailsActual = clientAuthenticationService.registrationAuthenticationService("abcd","mail12345",null, clientTable);
		assertEquals(clientSendBackDetailsExpected.getMessage(), clientSendBackDetailsActual.getMessage());
	}

}
