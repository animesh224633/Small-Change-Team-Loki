package com.smallchange.restService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import com.smallchange.uimodel.TradeHistory;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"}) 
public class TradeHistoryServiceE2ETest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;  
	
	@Test
	public void testQueryForTradeHistoryByClientId() {
		String request = "/tradeHistory/1";
		TradeHistory expectedTradeHistory = new TradeHistory("AMAZON", "AMZN",10, "BUY",new BigDecimal("569.70000000000000000000000000000000") , "STOCK");

		ResponseEntity<TradeHistory[]> response = 
				restTemplate.getForEntity(request, TradeHistory[].class);
		System.out.println(response.getBody()[0]);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
		assertThat(response.getBody()[0], is(equalTo(expectedTradeHistory)));
	}
	
}

