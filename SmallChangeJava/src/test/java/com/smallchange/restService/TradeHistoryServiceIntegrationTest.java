package com.smallchange.restService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.smallchange.uimodel.TradeHistory;

@SpringBootTest
@Transactional
public class TradeHistoryServiceIntegrationTest {

	@Autowired
	private TradeHistoryService service;

	@Autowired
	private JdbcTemplate jdbcTemplate;  
	
	@Test
	void testQueryForAllPresidentsSuccess() {
		TradeHistory expectedTradeHistory = new TradeHistory("AMAZON", "AMZN",10, "BUY",new BigDecimal("5694.70000000000000000000000000000000") , "STOCK");

		ResponseEntity<List<TradeHistory>> responseStatus = service.queryTradeHistoryByClientId("1");
		
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<TradeHistory> tradeHistoryList = responseStatus.getBody();
		assertThat(tradeHistoryList.size(), is(equalTo(2)));
		for (TradeHistory tradeHistory: tradeHistoryList) {
			assertThat(tradeHistory, is(notNullValue()));
		}
		assertThat(tradeHistoryList.get(0), is(equalTo(expectedTradeHistory)));
	}

	@Test
	void testQueryForTradeHistoryInvalidClientId() {
		ResponseEntity<List<TradeHistory>> responseStatus = service.queryTradeHistoryByClientId("0");
		
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<TradeHistory> tradeHistoryList = responseStatus.getBody();
		assertThat(tradeHistoryList.size(), is(equalTo(0)));
		}
}

