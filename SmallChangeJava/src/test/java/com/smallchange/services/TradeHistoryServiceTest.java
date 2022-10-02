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
	
	@DisplayName("should throw error when order table is null")
	@Test
	void testNullOrderTable() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", null, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Order history table cannot be null");
	}
	
	
	@DisplayName("should throw error when instrument table is null")
	@Test
	void testNullInstrumentTable() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList, null);
		});
		assertEquals(e.getMessage(), "instrument table cannot be null");
	}
	
	@DisplayName("should throw error when order table is empty")
	@Test
	void testEmptyOrderTable() throws Exception{
		List<OrderDAO> orders=new ArrayList<>();
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orders, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "order table cannot be empty");
	}
	
	@DisplayName("should throw error when instrument table is empty")
	@Test
	void testEmptyInstrumentTable() throws Exception{
		List<InstrumentDAO> instruments=new ArrayList<>();
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList, instruments);
		});
		assertEquals(e.getMessage(), "instrument table cannot be empty");
	}
	
	@DisplayName("Name cannot be null")
	@Test
	void testNullName() throws Exception {
		
		List<InstrumentDAO> instrumentDAOList1=new ArrayList<>();
		InstrumentDAO instrumentDAOs=new InstrumentDAO();
		instrumentDAOs.setCategory("Stocks");
		instrumentDAOs.setCode("AMZN");
		instrumentDAOs.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAOs.setName(null);
		instrumentDAOList1.add(instrumentDAOs);
		
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList, instrumentDAOList1);
		});
		assertEquals(e.getMessage(), "Name cannot be null");
	}
	
	@DisplayName("Name cannot be empty")
	@Test
	void testEmptyName() throws Exception {
		List<InstrumentDAO> instrumentDAOList1=new ArrayList<>();
		InstrumentDAO instrumentDAOs=new InstrumentDAO();
		instrumentDAOs.setCategory("Stocks");
		instrumentDAOs.setCode("AMZN");
		instrumentDAOs.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAOs.setName("");
		instrumentDAOList1.add(instrumentDAOs);
		
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList, instrumentDAOList1);
		});
		assertEquals(e.getMessage(), "Name cannot be empty");
	}
	
	@DisplayName("Asset class cannot be null")
	@Test
	void testNullAssetClass() throws Exception {
		
		List<InstrumentDAO> instrumentDAOList1=new ArrayList<>();
		InstrumentDAO instrumentDAOs=new InstrumentDAO();
		instrumentDAOs.setCategory(null);
		instrumentDAOs.setCode("AMZN");
		instrumentDAOs.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAOs.setName("AMAZON");
		instrumentDAOList1.add(instrumentDAOs);
		
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList, instrumentDAOList1);
		});
		assertEquals(e.getMessage(), "Asset class cannot be null");
	}
	
	@DisplayName("Asset class cannot be empty")
	@Test
	void testEmptyAssetClass() throws Exception {
		List<InstrumentDAO> instrumentDAOList1=new ArrayList<>();
		InstrumentDAO instrumentDAOs=new InstrumentDAO();
		instrumentDAOs.setCategory("");
		instrumentDAOs.setCode("AMZN");
		instrumentDAOs.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAOs.setName("AMAZON");
		instrumentDAOList1.add(instrumentDAOs);
		
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList, instrumentDAOList1);
		});
		assertEquals(e.getMessage(), "Asset class cannot be empty");
	}
	
	@DisplayName("Code cannot be null")
	@Test
	void testNullCode() throws Exception {
		List<OrderDAO> orderDAOList1 = new ArrayList<>();
		OrderDAO orderDAOs = new OrderDAO();
		orderDAOs.setClientId("loki");
		orderDAOs.setCode(null);
		orderDAOs.setQuantity(3);
		orderDAOs.setBuyPrice(BigDecimal.valueOf(100));
		orderDAOs.setDirection("Buy");
		orderDAOList1.add(orderDAOs);
		
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList1, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Code cannot be null");
	}
	
	@DisplayName("Code cannot be empty")
	@Test
	void testEmptyCode() throws Exception {
		List<OrderDAO> orderDAOList1 = new ArrayList<>();
		OrderDAO orderDAOs = new OrderDAO();
		orderDAOs.setClientId("loki");
		orderDAOs.setCode("");
		orderDAOs.setQuantity(3);
		orderDAOs.setBuyPrice(BigDecimal.valueOf(100));
		orderDAOs.setDirection("Buy");
		orderDAOList1.add(orderDAOs);
		
		
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList1, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Code cannot be empty");
	}
	
	@DisplayName("Quantity cannot be negative")
	@Test
	void testNegativeQuantity() throws Exception {
		List<OrderDAO> orderDAOList1 = new ArrayList<>();
		OrderDAO orderDAOs = new OrderDAO();
		orderDAOs.setClientId("loki");
		orderDAOs.setCode("AMZN");
		orderDAOs.setQuantity(-1);
		orderDAOs.setBuyPrice(BigDecimal.valueOf(100));
		orderDAOs.setDirection("Buy");
		orderDAOList1.add(orderDAOs);
		
		
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList1, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Quantity cannot be negative");
	}
	
	@DisplayName("Price cannot be null")
	@Test
	void testNullPrice() throws Exception {
		List<OrderDAO> orderDAOList1 = new ArrayList<>();
		OrderDAO orderDAOs = new OrderDAO();
		orderDAOs.setClientId("loki");
		orderDAOs.setCode("AMZN");
		orderDAOs.setQuantity(3);
		orderDAOs.setBuyPrice(null);
		orderDAOs.setDirection("Buy");
		orderDAOList1.add(orderDAOs);
		
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList1, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Price cannot be null");
	}
	
	@DisplayName("Price cannot be negative")
	@Test
	void testNegativePrice() throws Exception {
		List<OrderDAO> orderDAOList1 = new ArrayList<>();
		OrderDAO orderDAOs = new OrderDAO();
		orderDAOs.setClientId("loki");
		orderDAOs.setCode("AMZN");
		orderDAOs.setQuantity(3);
		orderDAOs.setBuyPrice(BigDecimal.valueOf(-1));
		orderDAOs.setDirection("Buy");
		orderDAOList1.add(orderDAOs);
		
		
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList1, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Price cannot be negative");
	}

	@DisplayName("Type cannot be null")
	@Test
	void testNullType() throws Exception {
		List<OrderDAO> orderDAOList1 = new ArrayList<>();
		OrderDAO orderDAOs = new OrderDAO();
		orderDAOs.setClientId("loki");
		orderDAOs.setCode("AMZN");
		orderDAOs.setQuantity(3);
		orderDAOs.setBuyPrice(BigDecimal.valueOf(100));
		orderDAOs.setDirection(null);
		orderDAOList1.add(orderDAOs);
		
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList1, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Type cannot be null");
	}
	
	@DisplayName("Type cannot be empty")
	@Test
	void testEmptyType() throws Exception {
		List<OrderDAO> orderDAOList1 = new ArrayList<>();
		OrderDAO orderDAOs = new OrderDAO();
		orderDAOs.setClientId("loki");
		orderDAOs.setCode("AMZN");
		orderDAOs.setQuantity(3);
		orderDAOs.setBuyPrice(BigDecimal.valueOf(100));
		orderDAOs.setDirection("");
		orderDAOList1.add(orderDAOs);
		
		
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			tradeHistoryService.getTradeHistory("loki", orderDAOList1, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Type cannot be empty");
	}
	
	

}
