package com.smallchange.restService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.TradeHistory;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"}) 
class PortfolioServiceE2ETest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testGetPortfolio() {
		Portfolio portfolio=new Portfolio("AMAZON","AMZN",1,new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("-5593.90000000000000000000000000000000"),new BigDecimal("-98.22993309568547000000000000000000"),"STOCK"); 
		String request = "/portfolio/1";
		ResponseEntity<Portfolio[]> response = 
				restTemplate.getForEntity(request, Portfolio[].class);
		System.out.println(response.getBody()[0]);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
		assertThat(response.getBody()[0], is(equalTo(portfolio)));
	}

	@Test
	void testGetPortfolioInvalid() {
		Portfolio portfolio=new Portfolio("AMAZON","AMZN",1,new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("-5593.90000000000000000000000000000000"),new BigDecimal("-98.22993309568547000000000000000000"),"STOCK"); 
		String request = "/portfolio/0";
		ResponseEntity<Portfolio[]> response = 
				restTemplate.getForEntity(request, Portfolio[].class);
		//System.out.println(response.getBody()[0]);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}
	
	@Test
	void testGetPortfolioNull() {
		Portfolio portfolio=new Portfolio("AMAZON","AMZN",1,new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("5694.70000000000000000000000000000000"),new BigDecimal("100.80000000000000000000000000000000"),new BigDecimal("-5593.90000000000000000000000000000000"),new BigDecimal("-98.22993309568547000000000000000000"),"STOCK"); 
		String request = "/portfolio/null";
		ResponseEntity<Portfolio[]> response = 
				restTemplate.getForEntity(request, Portfolio[].class);
		//System.out.println(response.getBody()[0]);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
	}
}
