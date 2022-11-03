package com.smallchange.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.integration.mapper.TradeHistoryMapper;
import com.smallchange.uimodel.TradeHistory;

import java.util.List;

import org.slf4j.Logger;

@Repository
public class TradeHistoryDaoMyBatisImpl implements TradeHistoryMyBatisDao {

	@Autowired
	private Logger logger;

	@Autowired
	private TradeHistoryMapper tradeHistoryMapper;
	
	@Override
	public List<TradeHistory> getTradeHistory(String clientId) {
		
		System.out.println("Hello seneha");
		logger.debug("enter");
		
		return tradeHistoryMapper.getTradeHistory(clientId);
	}

	@Override
	public List<TradeHistory> getTradeHistoryBySell(String clientId) {
		// TODO Auto-generated method stub
		System.out.println("Hello seneha");
		logger.debug("enter");
		
		return tradeHistoryMapper.getTradeHistoryBySell(clientId);
	}

	@Override
	public List<TradeHistory> getTradeHistoryByBuy(String clientId) {
		// TODO Auto-generated method stub
		System.out.println("Hello seneha");
		logger.debug("enter");
		
		return tradeHistoryMapper.getTradeHistoryByBuy(clientId);
	}
}
