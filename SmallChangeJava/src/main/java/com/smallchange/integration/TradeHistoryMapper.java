package com.smallchange.integration;

import java.util.List;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.smallchange.uimodel.TradeHistory;

public interface TradeHistoryMapper {
	@Select("select i.name,o.code,o.quantity,o.direction,o.Buy_price,i.category from orders o join instrument i on o.code=i.code where client_id=#{client_id}")
	List<TradeHistory> getTradeHistory(String clientId);

}
