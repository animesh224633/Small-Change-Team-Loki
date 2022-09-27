package com.smallchange.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smallchange.dao.OrderDAO;
import com.smallchange.model.Portfolio;

class PortfolioServiceTest {
	
	private List<OrderDAO> orderDAOList;
	private OrderDAO orderDAO;
	private PortfolioService portfolioService;

	@BeforeEach
	void setup() {
		orderDAOList = new ArrayList<>();
		orderDAO = new OrderDAO();
		orderDAO.setClientId("loki");
		orderDAO.setCode("AMZN");
		orderDAO.setQuantity(3);
		orderDAO.setBuyPrice(new BigDecimal("100.00"));
		orderDAOList.add(orderDAO);
		portfolioService = new PortfolioService();
	}
	
	@Test
	void check() {
		List<Portfolio> portfolioList = portfolioService.getUserPortfolio("loki", orderDAOList);
		for(Portfolio portfolioListIterator : portfolioList) {
			System.out.println(portfolioListIterator.getPortfolioStockView().getCode());
			System.out.println(portfolioListIterator.getPortfolioStockView().getQuantity());
		}
		
	}

}
