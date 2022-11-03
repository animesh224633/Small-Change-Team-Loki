package com.smallchange.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.smallchange.uimodel.TradeHistory;

@Mapper
public interface TradeHistoryMapper {
		List<TradeHistory> getTradeHistory(String clientId);
		List<TradeHistory> getTradeHistoryBySell(String clientId);
		List<TradeHistory> getTradeHistoryByBuy(String clientId);

}
