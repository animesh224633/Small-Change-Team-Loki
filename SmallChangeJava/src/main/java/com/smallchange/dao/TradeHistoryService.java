package com.smallchange.dao;

import java.util.ArrayList;
import java.util.List;

import com.smallchange.model.Instrument;
import com.smallchange.model.Orders;
import com.smallchange.uimodel.TradeHistory;

public class TradeHistoryService {
	List<TradeHistory> TradeHistoryList = new ArrayList<>();
	
	public List<TradeHistory> getTradeHistory(String clientId, List<Orders> orderTable, List<Instrument> instrumentTable) throws Exception {
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		if(orderTable ==null) {
			throw new NullPointerException("Order history table cannot be null");
		}
		if(orderTable.size()==0) {
			throw new IllegalArgumentException("order table cannot be empty");
		}
		if(instrumentTable==null) {
			throw new NullPointerException("instrument table cannot be null");
		}
		if(instrumentTable.size()==0) {
			throw new IllegalArgumentException("instrument table cannot be empty");
		}
		Boolean flag=orderTable.stream().filter(o -> o.getClientId().equals(clientId)).findFirst().isPresent();
		if(flag) {
		for(Orders orderTableIterator: orderTable) {
			TradeHistory tradeHistory=new TradeHistory();
			if(clientId.equals(orderTableIterator.getClientId())) {
				tradeHistory.setCode(orderTableIterator.getCode());
				tradeHistory.setQuantity(orderTableIterator.getQuantity());
				tradeHistory.setPrice(orderTableIterator.getBuyPrice());
				tradeHistory.setType(orderTableIterator.getDirection());
				for(Instrument instrumentTableIterator: instrumentTable) {
					if(instrumentTableIterator.getCode().equals(orderTableIterator.getCode())) {
						tradeHistory.setName(instrumentTableIterator.getName());
						tradeHistory.setAssetClass(instrumentTableIterator.getCategory());
					}
				}
				TradeHistoryList.add(tradeHistory);
			}	
		}
		}
		else {
			throw new IllegalArgumentException("client does not have any trade History");
		}
		return TradeHistoryList;
	}

	

}
