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

import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.TradeHistory;

@SpringBootTest
@Transactional
class PortfolioServiceIntegrationTest {

	@Autowired
	private PortfolioService service;
	
	@Test
	void testGetPortfolio() {
		Portfolio expectedPortfolio=new Portfolio("AMAZON","AMZN",1,new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("-5593.90000000000000000000000000000000"),new BigDecimal("-98.22993309568547000000000000000000"),"STOCK"); 
		ResponseEntity<List<Portfolio>> responseStatus=service.queryPortfolioByClientId("1");
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<Portfolio> PortfolioList = responseStatus.getBody();
		assertThat(PortfolioList.size(), is(equalTo(6)));
		for (Portfolio portfolio: PortfolioList) {
			assertThat(portfolio, is(notNullValue()));
		}
		assertThat(PortfolioList.get(0), is(equalTo(expectedPortfolio)));
	}
	

}
