package com.smallchange.integration;


import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.uimodel.TradeHistory;

@SpringBootTest
@Transactional
public class TradeHistoryDaoMyBatisImplTest {

	@Autowired
	private TradeHistoryMyBatisDao dao;
	
	@Test
	void testQueryForAllTradeHistory() {
		TradeHistory expectedTradeHistory = new TradeHistory("AMAZON", "AMZN",10, "BUY",new BigDecimal("569.70000000000000000000000000000000") , "STOCK");
		List<TradeHistory> tradeHistoryList = dao.getTradeHistory("1");
		assertEquals(8, tradeHistoryList.size());
		for (TradeHistory tradeHistory: tradeHistoryList) {
			assertNotNull(tradeHistory);
		}
		assertTrue(tradeHistoryList.contains(expectedTradeHistory));
	}
	
	@Test
	void testQueryForTradeHistoryFailure() {
		assertTrue(dao.getTradeHistory(Integer.toString(99)).isEmpty());
	}
}

