package com.smallchange.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.exception.InsufficientHoldingsException;
import com.smallchange.model.Client;
import com.smallchange.model.Holdings;
import com.smallchange.model.Instrument;
import com.smallchange.model.Orders;
import com.smallchange.uimodel.*;
public class OrderService {
	public List<OrderDao> orders=new ArrayList<>();
	/*public List<OrderDAO> executeTrade(String clientId,Order o, List<OrderDAO> orderTable, List<InstrumentDAO> instrumentTable){
		if(o.getType()=="buy") {
			return buyTrade(clientId,o,orderTable,instrumentTable);
		}
		else {
			return sellTrade(clientId,o,orderTable,instrumentTable);
		}
	}*/
	public static List<SellInstrument> getSellTrade(List<Holdings> holdingsTable,List<Instrument> instrumentTable){
		List<SellInstrument> sellInstrumentList=new ArrayList<>();
		for(Holdings holdingsTableIterator: holdingsTable) {
			SellInstrument sellInstrument=new SellInstrument();
			sellInstrument.setCode(holdingsTableIterator.getCode());
			sellInstrument.setCategory(holdingsTableIterator.getCategory());
			for(Instrument instrumentTableIter: instrumentTable) {
				if(holdingsTableIterator.getCode()==instrumentTableIter.getCode())
				sellInstrument.setCurrentPrice(instrumentTableIter.getCurrentPrice());
			}
			sellInstrument.setName(holdingsTableIterator.getName());
			sellInstrumentList.add(sellInstrument);
			System.out.println("Sell Instrument List:");
			for(SellInstrument s:sellInstrumentList) {
				System.out.println("----------------------------------------------------");
				System.out.println("category:"+s.getCategory());
				System.out.println("code:"+s.getCode());
				System.out.println("current price:"+s.getCurrentPrice());
				System.out.println("name:"+s.getName());
			}
		}
		return sellInstrumentList;
	}
	public static List<BuyInstrument> getBuyTrade(List<Instrument> instrumentTable){
		List<BuyInstrument> buyInstrumentList=new ArrayList<>();
		for(Instrument instrumentTableIter: instrumentTable) {
			BuyInstrument buyInstrument=new BuyInstrument();
			buyInstrument.setCategory(instrumentTableIter.getCategory());
			buyInstrument.setCode(instrumentTableIter.getCode());
			buyInstrument.setCurrentPrice(instrumentTableIter.getCurrentPrice());
			buyInstrument.setName(instrumentTableIter.getName());
			buyInstrumentList.add(buyInstrument);
		}
		System.out.println("Buy Instrument List:");
		for(BuyInstrument b:buyInstrumentList) {
			System.out.println("----------------------------------------------------");
			System.out.println("category:"+b.getCategory());
			System.out.println("code:"+b.getCode());
			System.out.println("current price:"+b.getCurrentPrice());
			System.out.println("name:"+b.getName());
		}
		return buyInstrumentList;
	}
	public static String putSellTrade(String clientId,List<Client> clientTable, List<Orders> orderTable,OrderDao so,List<Holdings> holdingsTable) throws InsufficientHoldingsException {
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		if(so==null) {
			throw new NullPointerException("Order cannot be null");
		}
		boolean flag=false;
		for(Client client:clientTable) {
			if(clientId == client.getClientId()) {
				flag=true;
				System.out.println("Client ID:"+clientId);
			BigDecimal oldWalletMoney=client.getClientSmallChangeWallet();
			BigDecimal newWalletMoney=(so.getPrice().multiply(new BigDecimal(so.getQuantity()))).add(oldWalletMoney);
			client.setClientSmallChangeWallet(newWalletMoney);
			System.out.println("Old Wallet amount:"+oldWalletMoney);
			System.out.println("New Wallet amount:"+newWalletMoney);
			}
		}
		if(!flag) {
			throw new IllegalArgumentException("Client doesn't exist");
		}
		
		Orders orderDAO = new Orders();
		orderDAO.setBuyPrice(so.getPrice());
		orderDAO.setClientId(clientId);
		orderDAO.setCode(so.getCode());
		orderDAO.setDirection("sell");
		orderDAO.setQuantity(so.getQuantity());
		orderDAO.setOrderId(UUID.randomUUID().toString());
		orderDAO.setTimestamp(LocalDate.now());
		orderTable.add(orderDAO);
		for(Holdings holdingTableIter:holdingsTable) {
			if(holdingTableIter.getClientId().equals(clientId)&&holdingTableIter.getCode().equals(so.getCode())) {
				if(so.getQuantity() > holdingTableIter.getQuantity()) {
					throw new InsufficientHoldingsException("You do not have enough holdings to execute this sell trade");
				}
				if(so.getQuantity() == holdingTableIter.getQuantity()) {
					holdingsTable.remove(holdingTableIter);
					break;
				}
				//holdingTableIter.setClientId(clientId);
				//holdingTableIter.setCode(so.getCode());
				//BigDecimal totalNewBuyPrice = BigDecimal.valueOf(bo.getQuantity()).multiply(bo.getPrice());
				//BigDecimal totalOldBuyPrice = holdingTableIter.getBuyPrice().multiply(BigDecimal.valueOf(holdingTableIter.getQuantity()));
				//BigDecimal newBuyPrice = (totalNewBuyPrice.add(totalOldBuyPrice)).divide((BigDecimal.valueOf(bo.getQuantity()).add(BigDecimal.valueOf(holdingTableIter.getQuantity()))));
				//holdingTableIter.setBuyPrice(newBuyPrice);
				else {
				holdingTableIter.setQuantity(holdingTableIter.getQuantity()- so.getQuantity());
				//holdingsTable.add(holdingTableIter);
				break;
				}
			}
		}
		//HoldingsDAO holdingsDAO = new HoldingsDAO();
		/*holdingsDAO.setClientId(clientId);
		holdingsDAO.setCode(so.getCode());
		holdingsDAO.setBuyPrice(so.getPrice());
		holdingsDAO.setQuantity(so.getQuantity());
		holdingsDAO.setName(so.getName());
		holdingsDAO.setHoldingId(UUID.randomUUID().toString());
		holdingsTable.add(holdingsDAO);*/
		System.out.println("Orders table after sell trade:");
		//String s= orderTable.
		//orderTable.forEach();
		System.out.println("No. of Orders:"+orderTable.size());
		for(Orders o:orderTable) {
			System.out.println("----------------------------------------------------");
			System.out.println("Order ID:"+o.getOrderId());
			System.out.println("Client ID:"+o.getClientId());
			System.out.println("Code:"+o.getCode());
			System.out.println("Quantity:"+o.getQuantity());
			System.out.println("Direction:"+o.getDirection());
		
		}
		System.out.println("Holdings table after sell trade:");
		System.out.println("No. of Holdings:"+holdingsTable.size());
		for(Holdings h:holdingsTable) {
			System.out.println("----------------------------------------------------");
			System.out.println(h.getHoldingId());
			System.out.println(h.getClientId());
			System.out.println(h.getCode());
			System.out.println(h.getName());
			System.out.println(h.getCategory());
			System.out.println(h.getQuantity());		
		}
	return "Success";
	}
	
	public static String putBuyTrade(String clientId,List<Client> clientTable, List<Orders> orderTable, List<Instrument> instrumentTable,OrderDao bo,List<Holdings> holdingsTable) throws InsufficientFundsException {
	
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		if(bo==null) {
			throw new NullPointerException("Order cannot be null");
		}
		boolean flag=false;
		for(Client client:clientTable) {
			if(clientId == client.getClientId()) {
				flag=true;
				System.out.println("Client ID:"+clientId);
			if((bo.getPrice().multiply(new BigDecimal(bo.getQuantity()))).compareTo(client.getClientSmallChangeWallet()) >0 ){
				throw new InsufficientFundsException("You do not have enough wallet balance money to execute this buy trade");
			}
			BigDecimal oldWalletMoney=client.getClientSmallChangeWallet();
			BigDecimal newWalletMoney=(oldWalletMoney).subtract(bo.getPrice().multiply(new BigDecimal(bo.getQuantity())));
			client.setClientSmallChangeWallet(newWalletMoney);
			System.out.println("Old Wallet amount:"+oldWalletMoney);
			System.out.println("New Wallet amount:"+newWalletMoney);
			}
		}
		
		if(!flag) {
			throw new IllegalArgumentException("Client doesn't exist");
		}
		boolean flag1=false;
		Orders orderDAO = new Orders();
		orderDAO.setBuyPrice(bo.getPrice());
		orderDAO.setClientId(clientId);
		orderDAO.setCode(bo.getCode());
		orderDAO.setQuantity(bo.getQuantity());
		orderDAO.setDirection("buy");
		orderDAO.setOrderId(UUID.randomUUID().toString());
		orderDAO.setTimestamp(LocalDate.now());
		orderTable.add(orderDAO);
			for(Holdings holdingTableIter:holdingsTable) {
				if(holdingTableIter.getClientId().equals(clientId)) {
						if(holdingTableIter.getCode().equals(bo.getCode())) {
							flag1=true;
					//holdingTableIter.setClientId(clientId);
					//holdingTableIter.setCode(bo.getCode());
					BigDecimal totalNewBuyPrice = BigDecimal.valueOf(bo.getQuantity()).multiply(bo.getPrice());
					BigDecimal totalOldBuyPrice = holdingTableIter.getBuyPrice().multiply(BigDecimal.valueOf(holdingTableIter.getQuantity()));
					BigDecimal newBuyPrice = (totalNewBuyPrice.add(totalOldBuyPrice)).divide((BigDecimal.valueOf(bo.getQuantity()).add(BigDecimal.valueOf(holdingTableIter.getQuantity()))));
					holdingTableIter.setBuyPrice(newBuyPrice);
					holdingTableIter.setQuantity(bo.getQuantity() + holdingTableIter.getQuantity());
					//holdingTableIter.setHoldingId(UUID.randomUUID().toString());
					//holdingsTable.add(holdingTableIter);
					break;
				}
						
			}
			}
			if(!flag1) {	
			Holdings holdingsDAO = new Holdings();
			holdingsDAO.setClientId(clientId);
			holdingsDAO.setCode(bo.getCode());
			holdingsDAO.setBuyPrice(bo.getPrice());
			holdingsDAO.setQuantity(bo.getQuantity());
			holdingsDAO.setName(bo.getName());
			holdingsDAO.setHoldingId(UUID.randomUUID().toString());
			holdingsTable.add(holdingsDAO);
			}
			System.out.println("Orders table after buy trade:");
			//orderTable.forEach();
			System.out.println("No. of Orders:"+orderTable.size());
			for(Orders o:orderTable) {
				System.out.println("----------------------------------------------------");
				System.out.println("Order ID:"+o.getOrderId());
				System.out.println("Client ID:"+o.getClientId());
				System.out.println("Code:"+o.getCode());
				System.out.println("Quantity:"+o.getQuantity());
				System.out.println("Direction:"+o.getDirection());
			
			}
			System.out.println("Holdings table after buy trade:");
			System.out.println("No. of Holdings:"+holdingsTable.size());
			for(Holdings h:holdingsTable) {
				System.out.println("----------------------------------------------------");
				System.out.println(h.getHoldingId());
				System.out.println(h.getClientId());
				System.out.println(h.getCode());
				System.out.println(h.getName());
				System.out.println(h.getCategory());
				System.out.println(h.getQuantity());		
			}
		return "Success";
	
	}	
}
