package com.smallchange.services;

import java.util.ArrayList;
import java.util.List;

import com.smallchange.dao.InstrumentDAO;
import com.smallchange.dao.OrderDAO;
import com.smallchange.model.TradeHistory;

public class TradeHistoryService {
	List<TradeHistory> TradeHistoryList = new ArrayList<>();
	
	public List<TradeHistory> getTradeHistory(String clientId, List<OrderDAO> orderTable, List<InstrumentDAO> instrumentTable) throws Exception {
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		Boolean flag=orderTable.stream().filter(o -> o.getClientId().equals(clientId)).findFirst().isPresent();
		if(flag) {
		for(OrderDAO orderTableIterator: orderTable) {
			TradeHistory tradeHistory=new TradeHistory();
			if(clientId.equals(orderTableIterator.getClientId())) {
				tradeHistory.setCode(orderTableIterator.getCode());
				tradeHistory.setQuantity(orderTableIterator.getQuantity());
				tradeHistory.setPrice(orderTableIterator.getBuyPrice());
				tradeHistory.setType(orderTableIterator.getDirection());
				for(InstrumentDAO instrumentTableIterator: instrumentTable) {
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
