package com.smallchange.services;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.smallchange.dao.ClientFinanceDetailsDAO;
import com.smallchange.dao.HoldingsDAO;
import com.smallchange.dao.InstrumentDAO;
import com.smallchange.dao.OrderDAO;
import com.smallchange.model.*;
public class OrderService {
	List<Order> orders=new ArrayList<>();
	TradeHistory tradeHistory=new TradeHistory();
	ClientFinanceDetailsDAO cf =new ClientFinanceDetailsDAO();
	public List<OrderDAO> executeTrade(String clientId,Order o, List<OrderDAO> orderTable, List<InstrumentDAO> instrumentTable){
		if(o.getType()=="buy") {
			return buyTrade(clientId,o,orderTable,instrumentTable);
		}
		else {
			return sellTrade(clientId,o,orderTable,instrumentTable);
		}
	}
	public List<BuyInstrument> getBuyTrade(List<InstrumentDAO> instrumentTable){
		List<BuyInstrument> buyInstrumentList=new ArrayList<>();
		for(InstrumentDAO instrumentTableIter: instrumentTable) {
			BuyInstrument buyInstrument=new BuyInstrument();
			buyInstrument.setCategory(instrumentTableIter.getCategory());
			buyInstrument.setCode(instrumentTableIter.getCode());
			buyInstrument.setCurrentPrice(instrumentTableIter.getCurrentPrice());
			buyInstrument.setName(instrumentTableIter.getName());
			buyInstrumentList.add(buyInstrument);
		}
		return buyInstrumentList;
	}
	
	public String putBuyTrade(String clientId, List<OrderDAO> orderTable, List<InstrumentDAO> instrumentTable,String code,int quantity, BigDecimal buyPrice,List<HoldingsDAO> holdingsTable) {
	
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.setBuyPrice(buyPrice);
		orderDAO.setClientId(clientId);
		orderDAO.setCode(code);
		orderDAO.setDirection("Buy");
		orderDAO.setOrderId(UUID.randomUUID().toString());
		orderDAO.setTimestamp(LocalDate.now());
		orderTable.add(orderDAO);
			for(HoldingsDAO holdingTableIter:holdingsTable) {
				if(holdingTableIter.getClientId().equals(clientId)&&holdingTableIter.getCode().equals(code)) {
					holdingTableIter.setClientId(clientId);
					holdingTableIter.setCode(code);
					BigDecimal totalNewBuyPrice = BigDecimal.valueOf(quantity).multiply(buyPrice);
					BigDecimal totalOldBuyPrice = holdingTableIter.getBuyPrice().multiply(BigDecimal.valueOf(holdingTableIter.getQuantity()));
					BigDecimal newBuyPrice = (totalNewBuyPrice.add(totalOldBuyPrice)).divide((BigDecimal.valueOf(quantity).add(BigDecimal.valueOf(holdingTableIter.getQuantity()))));
					holdingTableIter.setBuyPrice(newBuyPrice);
					holdingTableIter.setQuantity(quantity + holdingTableIter.getQuantity());
					holdingTableIter.setHoldingId(UUID.randomUUID().toString());
				}
			}
			HoldingsDAO holdingsDAO = new HoldingsDAO();
			holdingsDAO.setClientId(clientId);
			holdingsDAO.setCode(code);
			holdingsDAO.setBuyPrice(buyPrice);
			holdingsDAO.setQuantity(quantity);
			holdingsDAO.setHoldingId(UUID.randomUUID().toString());
			holdingsTable.add(holdingsDAO);
		return null;
	}
		
	
	public List<OrderDAO> buyTrade(String clientId,Order bo, List<OrderDAO> orderTable, List<InstrumentDAO> instrumentTable){
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		if(bo==null) {
			throw new NullPointerException("Order cannot be null");
		}
		//if(bo.getPrice().multiply(new BigDecimal(bo.getQuantity())) > )
		Boolean flag=orderTable.stream().filter(o -> o.getClientId().equals(clientId)).findFirst().isPresent();
		//if(flag) {
		for(InstrumentDAO instrumentTableIterator: instrumentTable) {
			OrderDAO od=new OrderDAO();
			//Order bo=new BuyOrder();
			if(bo.getCode().equals(instrumentTableIterator.getCode())) {
				
				
				od.setClientId(clientId);
				od.setDirection(bo.getType());
		//		for loop holdings (id,code)
		//		 hol quan * holding buyprice + new buy price * new quan / (holding quantity + new quantitu)
				od.setBuyPrice(bo.getPrice());
				od.setCode(bo.getCode());
				//if(od.getQuantity()>instrumentTableIterator.get)
				od.setQuantity(bo.getQuantity());
				//od.setOrderId(bo.get);
				od.setTimestamp(od.getTimestamp());
				orderTable.add(od);				
				}
		}
		//}
		//else {
		//	throw new IllegalArgumentException("client does not have any trade History");
		//}
		return orderTable;
		}
	public List<OrderDAO> sellTrade(String clientId,Order so, List<OrderDAO> orderTable, List<InstrumentDAO> instrumentTable){
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		if(so==null) {
			throw new NullPointerException("Order cannot be null");
		}
		Boolean flag=orderTable.stream().filter(o -> o.getClientId().equals(clientId)).findFirst().isPresent();
		if(flag) {
			for(OrderDAO orderTableIterator: orderTable) {
				if(clientId.equals(orderTableIterator.getClientId())) {
					
				}
			}
		}
		else {
				throw new IllegalArgumentException("client does not have any trade History");
			}
		return orderTable;
	}
}
