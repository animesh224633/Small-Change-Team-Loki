package com.smallchange.restService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.SmallChangeWalletAmount;
import com.smallchange.uimodel.TradeHistory;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"}) 
class SmallChangeWalletServiceE2ETest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@DisplayName("should get smallchange balance")
	@Test
	void testSmallChangeBalance() {
		String request = "/smallChangeWallet/1";
		ResponseEntity<SmallChangeWalletAmount> response = restTemplate.getForEntity(request, SmallChangeWalletAmount.class);
		System.out.println(response.getBody());
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
		assertEquals(new BigDecimal("3444.60000000000000000000000000000000"),response.getBody().getClientSmallChangeWallet());
	}
	
	@DisplayName("should get smallchange balance invalid")
	@Test
	void testSmallChangeBalanceInvalid() {
		String request = "/smallChangeWallet/0";
		ResponseEntity<SmallChangeWalletAmount> response = restTemplate.getForEntity(request, SmallChangeWalletAmount.class);
		System.out.println(response.getBody());
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
	}
	
	@DisplayName("should get smallchange bank details")
	@Test
	void testSmallChangeBankDetails() {
		String request = "/smallChangeWallet/getAccount/1";
		ResponseEntity<BankAccountDetails[]> response = restTemplate.getForEntity(request, BankAccountDetails[].class);
		System.out.println(response.getBody()[0]);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}
	
//	@DisplayName("should get smallchange bank updated wallet amount")
//	@Test
//	void testSmallChangeUpdateWalletAmount() {
//		String request = "/smallChangeWallet/update";
//		String jsonBody = "{\"name\":\"zozo100\",\"salary\":\"123\",\"age\":\"23\"}";
//		ResponseEntity<BankAccountDetails[]> response = restTemplate.getForEntity(request, BankAccountDetails[].class);
//		System.out.println(response.getBody()[0]);
//		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
//	}

	@DisplayName("should get smallchange bank details for invalid client")
	@Test
	void testSmallChangeBankDetailsInvalid() {
		String request = "/smallChangeWallet/getAccount/0";
		ResponseEntity<BankAccountDetails[]> response = restTemplate.getForEntity(request, BankAccountDetails[].class);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}
	
	
}
