package com.smallchange.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smallchange.dao.InstrumentDAO;
import com.smallchange.dao.OrderDAO;
import com.smallchange.model.Portfolio;

class PortfolioServiceTest {
	
	private List<OrderDAO> orderDAOList;
	private List<InstrumentDAO> instrumentDAOList;
	private OrderDAO orderDAO;
	private OrderDAO orderDAO2;
	private OrderDAO orderDAO3;
	private InstrumentDAO instrumentDAO;
	private InstrumentDAO instrumentDAO2;
	private InstrumentDAO instrumentDAO3;
	private PortfolioService portfolioService;

	@BeforeEach
	void setup() {
		orderDAOList = new ArrayList<>();
		instrumentDAOList = new ArrayList<>();
		instrumentDAO = new InstrumentDAO();
		instrumentDAO.setCode("AMZN");
		instrumentDAO.setName("Amazon");
		instrumentDAO.setCurrentPrice(new BigDecimal("100.00"));
		instrumentDAO.setCategory("Stock");
		instrumentDAOList.add(instrumentDAO);
		instrumentDAO2 = new InstrumentDAO();
		instrumentDAO2.setCode("MSFT");
		instrumentDAO2.setName("Microsoft");
		instrumentDAO2.setCurrentPrice(new BigDecimal("50.00"));
		instrumentDAO2.setCategory("Stock");
		instrumentDAOList.add(instrumentDAO2);
		instrumentDAO3 = new InstrumentDAO();
		instrumentDAO3.setCode("TSLA");
		instrumentDAO3.setName("Tesla");
		instrumentDAO3.setCurrentPrice(new BigDecimal("50.00"));
		instrumentDAO3.setCategory("Stock");
		instrumentDAOList.add(instrumentDAO3);
		orderDAO = new OrderDAO();
		orderDAO.setClientId("loki");
		orderDAO.setCode("AMZN");
		orderDAO.setQuantity(3);
		orderDAO.setBuyPrice(new BigDecimal("100.00"));
		orderDAOList.add(orderDAO);
		orderDAO2 = new OrderDAO();
		orderDAO2.setClientId("loki");
		orderDAO2.setCode("MSFT");
		orderDAO2.setQuantity(5);
		orderDAO2.setBuyPrice(new BigDecimal("50.00"));
		orderDAOList.add(orderDAO2);
		orderDAO3 = new OrderDAO();
		orderDAO3.setClientId("thor");
		orderDAO3.setCode("TSLA");
		orderDAO3.setQuantity(3);
		orderDAO3.setBuyPrice(new BigDecimal("50.00"));
		orderDAOList.add(orderDAO3);
		portfolioService = new PortfolioService();
	}
	
	@Test
	void check() {
		List<Portfolio> portfolioList = portfolioService.getUserPortfolio("loki", orderDAOList, instrumentDAOList);
		for(Portfolio portfolioListIterator : portfolioList) {
			System.out.println(portfolioListIterator.getPortfolioStockView().getCode());
			System.out.println(portfolioListIterator.getPortfolioStockView().getName());
			System.out.println(portfolioListIterator.getPortfolioStockView().getQuantity());
		}
		
	}

}
