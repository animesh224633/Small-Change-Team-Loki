package com.smallchange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.SmallChangeWalletAmount;
import com.smallchange.uimodel.TradeHistory;
import com.smallchange.uimodel.WalletUpdateDetails;
import com.smallchange.uimodel.WalletUpdateValues;

@SpringBootTest
@Transactional
class SmallChangeWalletMyBatisImplTest {
	
	@Autowired
	private SmallChangeWalletMyBatisImpl dao;
	

	@DisplayName("Should get wallet amount for existing client")
	@Test
	void testWalletAmount() {
		SmallChangeWalletAmount walletAmount=dao.getUserSmallChangeWalletAmount("1");
		System.out.println(walletAmount.getClientSmallChangeWallet());
		assertEquals(new BigDecimal("3444.60000000000000000000000000000000"),walletAmount.getClientSmallChangeWallet());
	}
	
	@DisplayName("Should get null wallet amount for invalid client")
	@Test
	void testWalletAmountInvalidClient() {
		assertNull(dao.getUserSmallChangeWalletAmount("0"));
		}
	
	@DisplayName("Should get bank account details")
	@Test
	void testBankAccountDetails() {
		List<BankAccountDetails> bankAccountDetailsList=dao.getBankAccountDetails("1");
		System.out.println(bankAccountDetailsList);
		for (BankAccountDetails bankAccountDetails: bankAccountDetailsList) {
			assertNotNull(bankAccountDetails);
			System.out.println(bankAccountDetails);
		}
		assertEquals(2, bankAccountDetailsList.size());
	}
	
	@DisplayName("should check bank details is fetched")
	@Test
	void testBankDetailsFetched() {
		List<BankAccountDetails> bankAccountDetailsList=dao.getBankAccountDetails("1");
		BankAccountDetails bankDetails=new BankAccountDetails("122354D",new BigDecimal("45645444.60000000000000000000000000000000"));
		assertTrue(bankAccountDetailsList.contains(bankDetails));
	}
	
	@DisplayName("Should check bank details for invalid client")
	@Test
	void testBankAccountDetailsForInvalidClient() {
		List<BankAccountDetails> bankAccountDetailsList=dao.getBankAccountDetails("0");
		System.out.println(bankAccountDetailsList);
		for (BankAccountDetails bankAccountDetails: bankAccountDetailsList) {
			assertNotNull(bankAccountDetails);
			System.out.println(bankAccountDetails);
		}
		assertEquals(0, bankAccountDetailsList.size());
	}
	
	@DisplayName("Should check bank details for null client")
	@Test
	void testBankAccountDetailsForNullClient() {
		List<BankAccountDetails> bankAccountDetailsList=dao.getBankAccountDetails(null);
		System.out.println(bankAccountDetailsList);
		for (BankAccountDetails bankAccountDetails: bankAccountDetailsList) {
			assertNotNull(bankAccountDetails);
			System.out.println(bankAccountDetails);
		}
		assertEquals(0, bankAccountDetailsList.size());
	}
	
	@DisplayName("should update the amount")
	@Test
	void testUpdateSmallChangeWallet() {
		WalletUpdateValues walletUpdateValues=new WalletUpdateValues("1","122354D",new BigDecimal("33.00"));
		WalletUpdateDetails walletUpdateDetails=dao.updateSmallChangeWallet(walletUpdateValues);
		System.out.println(walletUpdateDetails.getMessage());
		assertEquals(walletUpdateDetails.getMessage(),"success");
	}
	
	
		

}
