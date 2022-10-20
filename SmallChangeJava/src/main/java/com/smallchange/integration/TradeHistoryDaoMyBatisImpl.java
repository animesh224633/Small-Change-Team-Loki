package com.smallchange.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.uimodel.TradeHistory;

import java.util.List;

import org.slf4j.Logger;

@Repository("tradeHistoryDao")
public class TradeHistoryDaoMyBatisImpl {

	@Autowired
	private Logger logger;

	@Autowired
	private TradeHistoryMapper tradeHistoryMapper;
	
	public List<TradeHistory> getTradeHistory(String clientId) {
		
		System.out.println("Hello seneha");
		logger.debug("enter");
		
		return tradeHistoryMapper.getTradeHistory(clientId);
	}
}
