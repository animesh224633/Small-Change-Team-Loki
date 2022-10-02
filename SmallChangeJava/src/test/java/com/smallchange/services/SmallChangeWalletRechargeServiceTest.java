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
import com.smallchange.dao.ClientFinanceDetailsDAO;
import com.smallchange.model.WalletUpdateDetails;

class SmallChangeWalletRechargeServiceTest {
	
	SmallChangeWalletRechargeService smallChangeWalletRechargeService;
	List<ClientFinanceDetailsDAO> clientFinanceDetailsTable;
	List<ClientDAO> clientTable;
	WalletUpdateDetails walletUpdateDetailsExpected;
	WalletUpdateDetails walletUpdateDetailsActual;
	ClientDAO clientDAO1;
	ClientDAO clientDAO2;
	ClientDAO clientDAO3;
	ClientFinanceDetailsDAO clientFinanceDetailsDAO1;
	ClientFinanceDetailsDAO clientFinanceDetailsDAO2;
	ClientFinanceDetailsDAO clientFinanceDetailsDAO3;
	String clientId1="", clientId2="", clientId3="";
	
	@BeforeEach
	void setup() {
		smallChangeWalletRechargeService = new SmallChangeWalletRechargeService();
		clientFinanceDetailsTable = new ArrayList<>();
		clientTable = new ArrayList<>();
		walletUpdateDetailsExpected = new WalletUpdateDetails();
		walletUpdateDetailsActual = new WalletUpdateDetails();
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
		clientFinanceDetailsDAO1 = new ClientFinanceDetailsDAO();
		clientFinanceDetailsDAO1.setClientId(clientId1);
		clientFinanceDetailsDAO1.setAccountName("ICICI");
		clientFinanceDetailsDAO1.setAccountBalance(new BigDecimal("200.00"));
		clientFinanceDetailsDAO2 = new ClientFinanceDetailsDAO();
		clientFinanceDetailsDAO2.setClientId(clientId1);
		clientFinanceDetailsDAO2.setAccountName("HDFC");
		clientFinanceDetailsDAO2.setAccountBalance(new BigDecimal("2000.00"));
		clientFinanceDetailsDAO3 = new ClientFinanceDetailsDAO();
		clientFinanceDetailsDAO3.setClientId(clientId2);
		clientFinanceDetailsDAO3.setAccountName("KOTAK");
		clientFinanceDetailsDAO3.setAccountBalance(new BigDecimal("250.00"));
	}
	
	@Test
	@DisplayName("Checking for scenario where every parameter is correct and sufficient funds are there and money is added")
	void check1() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO1);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO2);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO3);
		walletUpdateDetailsExpected.setMessage("Amount added to wallet successfully");
		walletUpdateDetailsActual = smallChangeWalletRechargeService.updateClientWallet(clientId1, "ICICI", new BigDecimal("200"), clientTable, clientFinanceDetailsTable);
		assertEquals(walletUpdateDetailsExpected.getMessage(), walletUpdateDetailsActual.getMessage());	
	}
	
	@Test
	@DisplayName("Checking for scenario where client had insufficient funds in account")
	void check2() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO1);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO2);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO3);
		walletUpdateDetailsExpected.setMessage("Cannot add money to wallet. Insuficient funds.");
		walletUpdateDetailsActual = smallChangeWalletRechargeService.updateClientWallet(clientId1, "ICICI", new BigDecimal("20000"), clientTable, clientFinanceDetailsTable);
		assertEquals(walletUpdateDetailsExpected.getMessage(), walletUpdateDetailsActual.getMessage());	
	}
	
	@Test
	@DisplayName("Checking for exception if account is not present")
	void check3() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO1);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO2);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO3);
		
		Exception e =assertThrows(IllegalArgumentException.class, () -> {
			smallChangeWalletRechargeService.updateClientWallet(clientId1, "SBI", new BigDecimal("20000"), clientTable, clientFinanceDetailsTable);
		});
		assertEquals("Account not found", e.getMessage());	
	}
	
	@Test
	@DisplayName("Checking for exception if wrong clientId is passed")
	void check4() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO1);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO2);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO3);
		
		Exception e =assertThrows(IllegalArgumentException.class, () -> {
			smallChangeWalletRechargeService.updateClientWallet("random", "ICICI", new BigDecimal("20000"), clientTable, clientFinanceDetailsTable);
		});
		assertEquals("ClientID not found", e.getMessage());	
	}
	
	@Test
	@DisplayName("Checking for exception if account of a different client is passed")
	void check5() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO1);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO2);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO3);
		
		Exception e =assertThrows(IllegalArgumentException.class, () -> {
			smallChangeWalletRechargeService.updateClientWallet(clientId1, "KOTAK", new BigDecimal("20000"), clientTable, clientFinanceDetailsTable);
		});
		assertEquals("Account not found", e.getMessage());	//KOTAK is the account for client2 hence throws error if client1 tries to add money
	}
	
	@Test
	@DisplayName("Checking for a different client where every parameter is correct and sufficient funds are there and money is added")
	void check6() {
		clientTable.add(clientDAO1);
		clientTable.add(clientDAO2);
		clientTable.add(clientDAO3);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO1);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO2);
		clientFinanceDetailsTable.add(clientFinanceDetailsDAO3);
		walletUpdateDetailsExpected.setMessage("Amount added to wallet successfully");
		walletUpdateDetailsActual = smallChangeWalletRechargeService.updateClientWallet(clientId2, "KOTAK", new BigDecimal("200"), clientTable, clientFinanceDetailsTable);
		assertEquals(walletUpdateDetailsExpected.getMessage(), walletUpdateDetailsActual.getMessage());	
	}

}
