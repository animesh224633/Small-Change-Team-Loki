package com.smallchange.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smallchange.model.*;
import com.smallchange.dao.InstrumentDAO;
import com.smallchange.dao.OrderDAO;

public class OrderServiceTest {
	private List<OrderDAO> orderDAOList;
	private List<InstrumentDAO> instrumentDAOList;
	private InstrumentDAO instrumentDAO;
	private OrderService orderService;
	private OrderDAO orderDAO;
	private Order bo;

	@BeforeEach
	void setup() {
		orderDAOList = new ArrayList<>();
		orderDAO = new OrderDAO();
		orderDAO.setClientId("loki");
		orderDAO.setCode("AMZN");
		orderDAO.setQuantity(3);
		orderDAO.setBuyPrice(new BigDecimal("100.00"));
		orderDAOList.add(orderDAO);
		
		OrderDAO orderDAO1 = new OrderDAO();
		orderDAO1.setClientId("seneha");
		orderDAO1.setCode("AMZN");
		orderDAO1.setQuantity(4);
		orderDAO1.setBuyPrice(new BigDecimal("100.00"));
		orderDAOList.add(orderDAO1);
		
		
		OrderDAO orderDAO2 = new OrderDAO();
		orderDAO2.setClientId("loki");
		orderDAO2.setCode("MF");
		orderDAO2.setQuantity(5);
		orderDAO2.setBuyPrice(new BigDecimal("110.00"));
		orderDAOList.add(orderDAO2);
		
		
		
		instrumentDAOList=new ArrayList<>();
		instrumentDAO=new InstrumentDAO();
		instrumentDAO.setCategory("Stocks");
		instrumentDAO.setCode("AMZN");
		instrumentDAO.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAO.setName("AMAZON");
		instrumentDAOList.add(instrumentDAO);
		instrumentDAOList=new ArrayList<>();
		instrumentDAO=new InstrumentDAO();
		instrumentDAO.setCategory("Stocks");
		instrumentDAO.setCode("APP");
		instrumentDAO.setCurrentPrice(BigDecimal.valueOf(240));
		instrumentDAO.setName("APPLE");
		instrumentDAOList.add(instrumentDAO);
		
		InstrumentDAO instrumentDAO1=new InstrumentDAO();
		instrumentDAO1.setCategory("Stocks");
		instrumentDAO1.setCode("MF");
		instrumentDAO1.setCurrentPrice(BigDecimal.valueOf(100));
		instrumentDAO1.setName("Mutual fund");
		instrumentDAOList.add(instrumentDAO1);
		
		bo=new Order();
		bo.setName("APPLE");
		bo.setCode("APP");
		bo.setPrice(new BigDecimal("240.00"));
		bo.setQuantity(2);
		bo.setCode("buy");
		
		orderService=new OrderService();
	}
	@DisplayName("should throw error when non existing client id is given")
	@Test
	void testnoexistingclientId() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			orderService.sellTrade("loki1", bo, orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "client does not have any trade History");
	}
	
	@DisplayName("should throw error when client id is null")
	@Test
	void testNullCLientId() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			orderService.buyTrade(null,bo, orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "clientId cannot be null");
	}
	
	@DisplayName("should throw error when client id is empty")
	@Test
	void testEmptyCLientId() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			orderService.buyTrade("", bo,orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "clientId cannot be empty");
	}
	@DisplayName("should throw error when client id is null")
	@Test
	void testNullOrderBuy() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			orderService.buyTrade("loki",null, orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Order cannot be null");
	}
	@DisplayName("should throw error when client id is null")
	@Test
	void testNullOrderSell() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			orderService.sellTrade("loki",null, orderDAOList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "Order cannot be null");
	}
	@DisplayName("should successfully complete buy trade operation")
	@Test
	void testBuyTrade() {
		OrderDAO no= new OrderDAO();
		no.setCode("APP");
		no.setQuantity(2);
		no.setBuyPrice(new BigDecimal("240.00"));
		no.setClientId("loki");
		//no.setOrderId("123");
		no.setTimestamp(LocalDate.now());
		no.setDirection("buy");
		List<OrderDAO>tempList=orderDAOList;
		tempList.add(no);
		assertEquals(tempList,orderService.buyTrade("loki", bo, orderDAOList, instrumentDAOList));
	}
	
}
