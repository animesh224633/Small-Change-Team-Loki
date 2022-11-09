package com.smallchange.restService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.smallchange.dao.SmallChangeWalletRechargeService;
import com.smallchange.uimodel.SmallChangeWalletAmount;

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
	void testWalletBalanceInvalidClient() {
		assertThrows(ResponseStatusException.class, () -> {
			service.getSmallChangeWalletBalance("0");
		});
		
		//assertThat(responseStatus, is(equalTo(HttpStatus.OK)));
		//System.out.println(responseStatus);
		//assertEquals(new BigDecimal("3444.60000000000000000000000000000000"),responseStatus.getClientSmallChangeWallet());
	}

}
