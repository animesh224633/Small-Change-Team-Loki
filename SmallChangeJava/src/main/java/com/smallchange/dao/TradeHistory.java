package com.smallchange.dao;

import java.util.List;


public interface TradeHistory {

	List<TradeHistory> getTradeHistory(String clientId);
}
