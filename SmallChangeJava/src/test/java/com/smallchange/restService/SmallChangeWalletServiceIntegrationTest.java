package com.smallchange.restService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.smallchange.dao.SmallChangeWalletRechargeService;
import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.SmallChangeWalletAmount;
import com.smallchange.uimodel.TradeHistory;
import com.smallchange.uimodel.WalletUpdateDetails;
import com.smallchange.uimodel.WalletUpdateValues;

@SpringBootTest
@Transactional
class SmallChangeWalletServiceIntegrationTest {

	@Autowired
	private SmallChangeWalletService service;
	
	@Test
	void testWalletBalance() {
		SmallChangeWalletAmount responseStatus=service.getSmallChangeWalletBalance("1");
		//assertThat(responseStatus, is(equalTo(HttpStatus.OK)));
		System.out.println(responseStatus);
		assertEquals(new BigDecimal("3444.60000000000000000000000000000000"),responseStatus.getClientSmallChangeWallet());
	}
	
	@Test
	void testWalletBalanceNull() {
		assertThrows(ResponseStatusException.class, () -> {
			service.getSmallChangeWalletBalance(null);
		});	
	}

	@Test
	void testWalletBalanceInvalidClient() {
		assertThrows(ResponseStatusException.class, () -> {
			service.getSmallChangeWalletBalance("0");
		});
		
	}
	
	@Test
	void testGetClientAccount() {
		ResponseEntity<List<BankAccountDetails>> responseStatus=service.getAccountDetails("1");
		System.out.println(responseStatus);
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<BankAccountDetails> accountDetailsList=responseStatus.getBody();
		for (BankAccountDetails accountDetails: accountDetailsList) {
			assertThat(accountDetails, is(notNullValue()));
		}
		BankAccountDetails expectedAccount=new BankAccountDetails("HDFC Bank",new BigDecimal("7776.99000000000000000000000000000000"));

		assertThat(accountDetailsList.get(0), is(equalTo(expectedAccount)));
		
	}
	
	@Test
	void testGetClientAccountInvalid() {
		ResponseEntity<List<BankAccountDetails>> responseStatus=service.getAccountDetails("0");
		System.out.println(responseStatus);
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<BankAccountDetails> accountDetailsList=responseStatus.getBody();
		assertEquals(accountDetailsList.size(), 0);
		
	}
	
	@Test
	void testGetClientAccountNull() {
		ResponseEntity<List<BankAccountDetails>> responseStatus=service.getAccountDetails(null);
		System.out.println(responseStatus);
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<BankAccountDetails> accountDetailsList=responseStatus.getBody();
		assertEquals(accountDetailsList.size(), 0);	
	}
	
	@Test
	void testWalletUpdateDetails() {
		WalletUpdateValues wallet=new WalletUpdateValues("1","HDFC Bank",new BigDecimal(10));
		WalletUpdateDetails responseStatus=service.updateSmallChangeWalletBalance(wallet);
		System.out.println(responseStatus.getMessage());
		assertEquals(responseStatus.getMessage(),"success");
	}
	
	@Test
	void testWalletUpdateDetailsInvalid() {
		WalletUpdateValues wallet=new WalletUpdateValues("0","HDFC Bank",new BigDecimal(10));
		assertThrows(ResponseStatusException.class, () -> {
			service.updateSmallChangeWalletBalance(wallet);
		});
		
	}
	
	@Test
	void testWalletUpdateDetailsNull() {
		WalletUpdateValues wallet=new WalletUpdateValues(null,"HDFC Bank",new BigDecimal(10));
		assertThrows(ResponseStatusException.class, () -> {
			service.updateSmallChangeWalletBalance(wallet);
		});
		
	}

}
