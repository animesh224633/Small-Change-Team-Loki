package com.smallchange.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smallchange.dao.InstrumentDAO;
import com.smallchange.dao.OrderDAO;
import com.smallchange.model.TradeHistory;

class TradeHistoryServiceTest {

	private List<OrderDAO> orderDAOList;
	private List<InstrumentDAO> instrumentDAOList;
	private InstrumentDAO instrumentDAO;
	private TradeHistoryService tradeHistoryService;
	private OrderDAO orderDAO;

	@BeforeEach
	void setup() {
		orderDAOList = new ArrayList<>();
		orderDAO = new OrderDAO();
		orderDAO.setClientId("loki");
		orderDAO.setCode("AMZN");
		orderDAO.setQuantity(3);
		orderDAO.setBuyPrice(BigDecimal.valueOf(100));
		orderDAO.setDirection("Buy");
		orderDAOList.add(orderDAO);
		
		OrderDAO orderDAO1 = new OrderDAO();
		orderDAO1.setClientId("seneha");
		orderDAO1.setCode("AMZN");
		orderDAO1.setQuantity(4);
		orderDAO1.setDirection("Buy");
		orderDAO1.setBuyPrice(BigDecimal.valueOf(100));
		orderDAOList.add(orderDAO1);
		
		
		OrderDAO orderDAO2 = new OrderDAO();
		orderDAO2.setClientId("loki");
		orderDAO2.setCode("MF");
		orderDAO2.setQuantity(5);
		orderDAO2.setDirection("Sell");
		orderDAO2.setBuyPrice(BigDecimal.valueOf(100));
		orderDAOList.add(orderDAO2);
		
		
		
		instrumentDAOList=new ArrayList<>();
		instrumentDAO=new InstrumentDAO();
		instrumentDAO.setCategory("Stocks");
		instrumentDAO.setCode("AMZN");
		instrumentDAO.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAO.setName("AMAZON");
		instrumentDAOList.add(instrumentDAO);
		
		InstrumentDAO instrumentDAO1=new InstrumentDAO();
		instrumentDAO1.setCategory("Stocks");
		instrumentDAO1.setCode("MF");
		instrumentDAO1.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAO1.setName("Mutual fund");
		instrumentDAOList.add(instrumentDAO1);
		
		tradeHistoryService=new TradeHistoryService();
	}
	
	@DisplayName("should test the lenght of history fetched")
	@Test
	void testBasicTradeHistoryLength() throws Exception {
		List<TradeHistory> tradeHistoryList=tradeHistoryService.getTradeHistory("seneha", orderDAOList, instrumentDAOList);
		assertEquals(1,tradeHistoryList.size());
		
	}
	
	@DisplayName("should test the data fetched")
	@Test
	void testBasicTradeHistoryData() throws Exception {
		List<TradeHistory> tradeHistory=new ArrayList<>();
		TradeHistory tradeHistoryData=new TradeHistory();
		tradeHistoryData.setAssetClass("Stocks");
		tradeHistoryData.setCode("AMZN");
		tradeHistoryData.setName("AMAZON");
		tradeHistoryData.setPrice(BigDecimal.valueOf(100));
		tradeHistoryData.setQuantity(4);
		tradeHistoryData.setType("Buy");
		tradeHistory.add(tradeHistoryData);
		List<TradeHistory> tradeHistoryList=tradeHistoryService.getTradeHistory("seneha", orderDAOList, instrumentDAOList);
		assertEquals(tradeHistory,tradeHistoryList);
		
	}
	
	@DisplayName("should throw error when non existing client id is given")
	@Test
	void testnoexistingclientId() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki1", orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "client does not have any trade History");
	}
	
	@DisplayName("should throw error when client id is null")
	@Test
	void testNullCLientId() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory(null, orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "clientId cannot be null");
	}
	
	@DisplayName("should throw error when client id is empty")
	@Test
	void testEmptyCLientId() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("", orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "clientId cannot be empty");
	}
	
	

}
