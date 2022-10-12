package com.smallchange.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smallchange.dao.OrderService;
import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.exception.InsufficientHoldingsException;
import com.smallchange.model.Client;
import com.smallchange.model.Holdings;
import com.smallchange.model.Instrument;
import com.smallchange.model.Orders;
import com.smallchange.uimodel.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.exception.InsufficientHoldingsException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.exception.InsufficientHoldingsException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {
	public List<Orders> orderDAOList;
	public List<Instrument> instrumentDAOList;
	public Instrument instrumentDAO;
	public List<Holdings> holdingsDAOList;
	public Holdings holdingDAO;
	public OrderService orderService;
	public Orders orderDAO;
	public OrderDao bo;
	public OrderDao so;
	public String hid1,hid2,hid3;
public List<Client> clientDAOList;
private Client clientDAO;
	@BeforeEach
	public void setup() {
		clientDAOList = new ArrayList<>();
		clientDAO = new Client();
		clientDAO.setClientId("loki");
		clientDAO.setClientMail("loki@gmail.com");
		clientDAO.setClientName("loki");
		clientDAO.setClientSmallChangeWallet(new BigDecimal("1000.00"));
		clientDAO.setPassword("loki123");
		clientDAOList.add(clientDAO);
		
		orderDAOList = new ArrayList<>();
		orderDAO = new Orders();
		orderDAO.setClientId("loki");
		orderDAO.setCode("AMZN");
		orderDAO.setQuantity(3);
		orderDAO.setBuyPrice(new BigDecimal("100.00"));
		orderDAO.setOrderId(UUID.randomUUID().toString());
		orderDAO.setDirection("buy");
		orderDAOList.add(orderDAO);
		
		Orders orderDAO1 = new Orders();
		orderDAO1.setClientId("seneha");
		orderDAO1.setCode("AMZN");
		orderDAO1.setQuantity(4);
		orderDAO1.setBuyPrice(new BigDecimal("100.00"));
		orderDAO1.setOrderId(UUID.randomUUID().toString());
		orderDAO1.setDirection("buy");
		orderDAOList.add(orderDAO1);
		
		
		Orders orderDAO2 = new Orders();
		orderDAO2.setClientId("loki");
		orderDAO2.setCode("MF");
		orderDAO2.setQuantity(5);
		orderDAO2.setBuyPrice(new BigDecimal("110.00"));
		orderDAO2.setOrderId(UUID.randomUUID().toString());
		orderDAO2.setDirection("buy");
		orderDAOList.add(orderDAO2);
		
		holdingsDAOList=new ArrayList<>();
		holdingDAO=new Holdings();
		holdingDAO.setCode("AMZN");
		holdingDAO.setHoldingId(UUID.randomUUID().toString());
		holdingDAO.setName("AMAZON");
		holdingDAO.setQuantity(3);
		holdingDAO.setBuyPrice(new BigDecimal("100.00"));
		holdingDAO.setClientId("loki");
		holdingDAO.setCategory("Stocks");
		holdingsDAOList.add(holdingDAO);
		hid1=holdingDAO.getHoldingId();
		
		Holdings holdingDAO1;
		holdingDAO1=new Holdings();
		holdingDAO1.setCode("MF");
		holdingDAO1.setHoldingId(UUID.randomUUID().toString());
		holdingDAO1.setName("MICROSOFT");
		holdingDAO1.setQuantity(5);
		holdingDAO1.setBuyPrice(new BigDecimal("110.00"));
		holdingDAO1.setClientId("loki");
		holdingDAO1.setCategory("Stocks");
		holdingsDAOList.add(holdingDAO1);
		hid2=holdingDAO1.getHoldingId();
		
		instrumentDAOList=new ArrayList<>();
		instrumentDAO=new Instrument();
		instrumentDAO.setCategory("Stocks");
		instrumentDAO.setCode("AMZN");
		instrumentDAO.setCurrentPrice(BigDecimal.valueOf(100.00));
		instrumentDAO.setName("AMAZON");
		instrumentDAOList.add(instrumentDAO);

		Instrument instrumentDAO2=new Instrument();
		instrumentDAO2=new Instrument();
		instrumentDAO2.setCategory("Stocks");
		instrumentDAO2.setCode("APP");
		instrumentDAO2.setCurrentPrice(BigDecimal.valueOf(240.00));
		instrumentDAO2.setName("APPLE");
		instrumentDAOList.add(instrumentDAO2);
		
		Instrument instrumentDAO1=new Instrument();
		instrumentDAO1.setCategory("Stocks");
		instrumentDAO1.setCode("MF");
		instrumentDAO1.setCurrentPrice(BigDecimal.valueOf(110.00));
		instrumentDAO1.setName("Microsoft");
		instrumentDAOList.add(instrumentDAO1);
		
		bo=new OrderDao();
		bo.setName("APPLE");
		bo.setCode("APP");
		bo.setPrice(new BigDecimal("240.00"));
		bo.setQuantity(2);
		bo.setType("buy");
		
		so=new OrderDao();
		so.setName("MICROSOFT");
		so.setCode("MF");
		so.setPrice(new BigDecimal("110.00"));
		so.setQuantity(2);
		so.setType("sell");
		
		//orderService=new OrderService();
	}
	@DisplayName("should throw error when non existing client id is given (buy)")
	@Test
	public void testnoexistingclientIdBuy() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			OrderService.putBuyTrade("loki1",clientDAOList, orderDAOList, instrumentDAOList,bo,holdingsDAOList);
		});
		assertEquals(e.getMessage(), "Client doesn't exist");
	}
	
	@DisplayName("should throw error when client id is null (buy)")
	@Test
	public void testNullCLientIdBuy() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			OrderService.putBuyTrade(null,clientDAOList, orderDAOList, instrumentDAOList,bo,holdingsDAOList);
			});
		assertEquals(e.getMessage(), "clientId cannot be null");
	}
	
	@DisplayName("should throw error when client id is empty (buy)")
	@Test
	public void testEmptyCLientIdBuy() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			OrderService.putBuyTrade("",clientDAOList, orderDAOList, instrumentDAOList,bo,holdingsDAOList);
			
		});
		assertEquals(e.getMessage(), "clientId cannot be empty");
	}
	@DisplayName("should throw error when order is null (buy)")
	@Test
	public void testNullOrderBuy() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			OrderService.putBuyTrade("loki",clientDAOList, orderDAOList, instrumentDAOList,null,holdingsDAOList);
			});
		assertEquals(e.getMessage(), "Order cannot be null");
	}
	@DisplayName("should throw error when non existing client id is given (sell)")
	@Test
	public void testnoexistingclientIdSell() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			OrderService.putSellTrade("loki1",clientDAOList, orderDAOList,so,holdingsDAOList);
		});
		assertEquals(e.getMessage(), "Client doesn't exist");
	}
	
	@DisplayName("should throw error when client id is null (sell)")
	@Test
	public void testNullCLientIdSell() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			OrderService.putSellTrade(null,clientDAOList, orderDAOList,so,holdingsDAOList);
			});
		assertEquals(e.getMessage(), "clientId cannot be null");
	}
	
	@DisplayName("should throw error when client id is empty (sell)")
	@Test
	public void testEmptyCLientIdSell() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			OrderService.putSellTrade("",clientDAOList, orderDAOList,so,holdingsDAOList);
			
		});
		assertEquals(e.getMessage(), "clientId cannot be empty");
	}
	@DisplayName("should throw error when order is null (sell)")
	@Test
	public void testNullOrderSell() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			OrderService.putSellTrade("loki",clientDAOList, orderDAOList,null,holdingsDAOList);
			});
		assertEquals(e.getMessage(), "Order cannot be null");
	}
	/*@DisplayName("should throw error when client id is null")
	@Test
	void testNullOrderSell() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			orderService.sellTrade("loki",null, orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Order cannot be null");
	}*/
	@DisplayName("should successfully complete BUY trade operation 1")
	@Test
	public void putBuyTradeTest() throws InsufficientFundsException {
		/*OrderDAO no= new OrderDAO();
		no.setCode("APP");
		no.setQuantity(2);
		no.setBuyPrice(new BigDecimal("240.00"));
		no.setClientId("loki");
		//no.setOrderId("123");
		no.setTimestamp(LocalDate.now());
		no.setDirection("buy");
		List<OrderDAO>tempList=orderDAOList;
		tempList.add(no);*/
		assertEquals("Success",
				OrderService.putBuyTrade("loki",clientDAOList, orderDAOList, instrumentDAOList,bo,holdingsDAOList));
	}
	@DisplayName("should successfully complete BUY trade operation 2")
	@Test
	public void putBuyTradeTest1() throws InsufficientFundsException {
		OrderDao bo1=new OrderDao();
		bo1.setName("AMAZON");
		bo1.setCode("AMZN");
		bo1.setPrice(new BigDecimal("100.00"));
		bo1.setQuantity(2);
		bo1.setType("buy");
		assertEquals("Success",
				OrderService.putBuyTrade("loki",clientDAOList, orderDAOList, instrumentDAOList,bo1,holdingsDAOList));
	}
	@DisplayName("should successfully complete SELL trade operation 1")
	@Test
	public void putSellTradeTest() throws  InsufficientHoldingsException {
		
		assertEquals("Success",
				OrderService.putSellTrade("loki",clientDAOList, orderDAOList,so,holdingsDAOList));
	}
	@DisplayName("should successfully complete SELL trade operation 2")
	@Test
	public void putSellTradeTest1() throws  InsufficientHoldingsException {
		OrderDao so1=new OrderDao();
		so1.setName("MICROSOFT");
		so1.setCode("MF");
		so1.setPrice(new BigDecimal("110.00"));
		so1.setQuantity(5);
		so1.setType("sell");
		assertEquals("Success",
				OrderService.putSellTrade("loki",clientDAOList, orderDAOList,so1,holdingsDAOList));
	}
	@DisplayName("should throw BUY EXCEPTION for Insufficient Funds")
	@Test
	public void putBuyTradeIFExceptionTest() {
		InsufficientFundsException e = assertThrows(InsufficientFundsException.class, () -> {
			OrderDao bo1=new OrderDao();
			bo1.setName("APPLE");
			bo1.setCode("APP");
			bo1.setPrice(new BigDecimal("240.00"));
			bo1.setQuantity(6);
			bo1.setType("buy");
			OrderService.putBuyTrade("loki",clientDAOList, orderDAOList, instrumentDAOList,bo1,holdingsDAOList);	
		});
		assertEquals(e.getMessage(), "You do not have enough wallet balance money to execute this buy trade");
	}
	@DisplayName("Should throw SELL EXCEPTION for Insufficient Holdings")
	@Test
	public void putSellTradeIHExceptionTest() {
		InsufficientHoldingsException e = assertThrows(InsufficientHoldingsException.class, () -> {
			OrderDao so1=new OrderDao();
			so1.setName("MICROSOFT");
			so1.setCode("MF");
			so1.setPrice(new BigDecimal("240.00"));
			so1.setQuantity(6);
			so1.setType("buy");
			OrderService.putSellTrade("loki",clientDAOList, orderDAOList,so1,holdingsDAOList);	
		
		});
		assertEquals(e.getMessage(),"You do not have enough holdings to execute this sell trade" );
	}
	@DisplayName("should successfully get Buy Instrument List")
	@Test
	public void getBuyTradeTest() {
		List<BuyInstrument> buyInstrumentList=new ArrayList<>();
		BuyInstrument buyInstrument2=new BuyInstrument();
		buyInstrument2.setCategory("Stocks");
		buyInstrument2.setCode("AMZN");
		buyInstrument2.setCurrentPrice(BigDecimal.valueOf(100.00));
		buyInstrument2.setName("AMAZON");
		buyInstrumentList.add(buyInstrument2);
		
		BuyInstrument buyInstrument1=new BuyInstrument();
		buyInstrument1.setCategory("Stocks");
		buyInstrument1.setCode("APP");
		buyInstrument1.setCurrentPrice(BigDecimal.valueOf(240.00));
		buyInstrument1.setName("APPLE");
		buyInstrumentList.add(buyInstrument1);
		
		BuyInstrument buyInstrument=new BuyInstrument();
		buyInstrument.setCategory("Stocks");
		buyInstrument.setCode("MF");
		buyInstrument.setCurrentPrice(BigDecimal.valueOf(110.00));
		buyInstrument.setName("Microsoft");
		buyInstrumentList.add(buyInstrument);
	
		assertEquals(buyInstrumentList,OrderService.getBuyTrade(instrumentDAOList));
	}
	@DisplayName("should successfully get Sell Instrument List")
	@Test
	public void getSellTradeTest() {
	
		List<SellInstrument> sellInstrumentList=new ArrayList<>();
		SellInstrument s=new SellInstrument();
		s.setCode("AMZN");
		s.setCategory("Stocks");
		s.setCurrentPrice(BigDecimal.valueOf(100.00));
		s.setName("AMAZON");
		
		SellInstrument s1=new SellInstrument();
		s1.setCode("MF");
		s1.setCategory("Stocks");
		s1.setCurrentPrice(BigDecimal.valueOf(110.00));
		s1.setName("MICROSOFT");
		sellInstrumentList.add(s);
		sellInstrumentList.add(s1);
		assertEquals(sellInstrumentList,OrderService.getSellTrade(holdingsDAOList, instrumentDAOList));
	}
	}

