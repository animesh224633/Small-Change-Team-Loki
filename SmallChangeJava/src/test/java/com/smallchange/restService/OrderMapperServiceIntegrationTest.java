package com.smallchange.restService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.BuyOrder;
import com.smallchange.uimodel.BuySellResponse;
import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.SellInstrument;
import com.smallchange.uimodel.SellOrder;

@SpringBootTest
@Transactional
class OrderMapperServiceIntegrationTest {

	@Autowired
	private OrderMapperService service;
	@Test
	void testgetSellInstrument() {
		ResponseEntity<List<SellInstrument>> responseStatus=service.querySellInstrument("1");
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<SellInstrument> SellInstrumentList = responseStatus.getBody();
		assertThat(SellInstrumentList.size(), is(equalTo(6)));
		for (SellInstrument sellInstrument: SellInstrumentList) {
			assertThat(sellInstrument, is(notNullValue()));
		}
	}
	
	@Test
	void testgetBuyInstrument() {
		ResponseEntity<List<BuyInstrument>> responseStatus=service.queryBuyInstrument();
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.OK)));
		List<BuyInstrument> BuyInstrumentList = responseStatus.getBody();
		assertThat(BuyInstrumentList.size(), is(equalTo(10)));
		for (BuyInstrument buyInstrument: BuyInstrumentList) {
			assertThat(buyInstrument, is(notNullValue()));
		}
	}
	
	@Test
	void testBuyUpdate() throws InsufficientFundsException {
		BuyOrder buy=new BuyOrder("AMZN",1,new BigDecimal("10"),"1","5",LocalDate.now(),"BUY","111");

		ResponseEntity<BuySellResponse> responseStatus=service.queryPutBuyTrade(buy);
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
		BuySellResponse buyInstrument = responseStatus.getBody();
		assertTrue(buyInstrument.isResponse());

	}
	
	@Test
	void testSellUpdate() throws InsufficientFundsException {
		SellOrder sell=new SellOrder("AMZN",1,new BigDecimal("10"),"1","5",LocalDate.now(),"BUY","111");

		ResponseEntity<BuySellResponse> responseStatus=service.queryPutSellTrade(sell);
		assertThat(responseStatus.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
		BuySellResponse sellInstrument = responseStatus.getBody();
		assertTrue(sellInstrument.isResponse());

	}

}
