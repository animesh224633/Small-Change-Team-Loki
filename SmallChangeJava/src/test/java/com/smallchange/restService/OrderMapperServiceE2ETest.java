package com.smallchange.restService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.BuyOrder;
import com.smallchange.uimodel.BuySellResponse;
import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.LoginClientDetails;
import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.SellInstrument;
import com.smallchange.uimodel.SellOrder;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"}) 
class OrderMapperServiceE2ETest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	void testGetSellInstrument() {
		SellInstrument sell=new SellInstrument("1101","1","AMZN","AMAZON","STOCK",1,new BigDecimal("100.80"));
		String request = "/orderMapper/sell/1";
		ResponseEntity<SellInstrument[]> response = 
				restTemplate.getForEntity(request, SellInstrument[].class);
		System.out.println(response.getBody()[0]);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
		assertThat(response.getBody()[0], is(equalTo(sell)));
	}

	@Test
	void testGetBuyInstrument() {
		BuyInstrument buy=new BuyInstrument("AMZN","AMAZON","STOCK",new BigDecimal("100.80"));
		String request = "/orderMapper/buy";
		ResponseEntity<BuyInstrument[]> response = 
				restTemplate.getForEntity(request, BuyInstrument[].class);
		System.out.println(response.getBody()[0]);
		assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
		assertThat(response.getBody()[0], is(equalTo(buy)));
	}
	
	@Test
	void testSellUpdate() {
		SellOrder sell=new SellOrder("AMZN",1,new BigDecimal("10"),"1","5",LocalDate.now(),"SELL","111");
		HttpEntity<SellOrder> requestEntity = new HttpEntity<>(sell);
		String request = "/orderMapper/sellUpdate";
		ResponseEntity<BuySellResponse> response=restTemplate.postForEntity(request, requestEntity, BuySellResponse.class);
		System.out.println("hello");
		assertTrue(response.getBody().isResponse());
	}
	
	@Test
	void testBuyUpdate() {
		BuyOrder buy=new BuyOrder("AMZN",1,new BigDecimal("10"),"1","5",LocalDate.now(),"BUY","111");
		HttpEntity<BuyOrder> requestEntity = new HttpEntity<>(buy);
		String request = "/orderMapper/buyUpdate";
		ResponseEntity<BuySellResponse> response=restTemplate.postForEntity(request, requestEntity, BuySellResponse.class);
		System.out.println("hello");
		assertTrue(response.getBody().isResponse());
	}
}
