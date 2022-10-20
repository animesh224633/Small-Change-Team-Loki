package com.smallchange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.uimodel.TradeHistory;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
class TradeHistoryDaoMyBatisImplTest {

	@Autowired
	private TradeHistoryDaoMyBatisImpl dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@DisplayName("Should check whether we are getting all the trade")
	@Test
	void getAll_Client() {
		List<TradeHistory> tradehistory=dao.getTradeHistory("1");
		assertEquals(tradehistory.size(),2);
	}
	
	@DisplayName("Should return 0 when client id is null")
	@Test
	void getclient_null() {
		List<TradeHistory> tradehistory=dao.getTradeHistory(null);
		assertEquals(tradehistory.size(),0);
	}

}
