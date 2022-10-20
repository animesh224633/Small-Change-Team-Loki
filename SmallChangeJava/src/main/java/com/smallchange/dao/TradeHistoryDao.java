package com.smallchange.dao;

import java.util.List;

import com.smallchange.uimodel.TradeHistory;


public interface TradeHistoryDao {

	public List<TradeHistory> getTradeHistory(String clientId);
}
