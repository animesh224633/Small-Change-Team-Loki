package com.smallchange.integration;


import java.util.List;

import com.smallchange.uimodel.TradeHistory;


public interface TradeHistoryMyBatisDao {

	public List<TradeHistory> getTradeHistory(String clientId);
}

