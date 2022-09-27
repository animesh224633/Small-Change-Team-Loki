package com.smallchange.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smallchange.dao.OrderDAO;
import com.smallchange.model.Portfolio;
import com.smallchange.model.PortfolioStock;

public class PortfolioService {
	
	List<Portfolio> portfolioList = new ArrayList<>();
	
	public List<Portfolio> getUserPortfolio(String clientId, List<OrderDAO> orderTable) {
		// Assuming that instrument category is stock; code needs to be modified further and check if 
		//category is stock then only add to portfoliostock otherwise add to portfoliomutualfund
		
		Portfolio portfolio = new Portfolio();
		for(OrderDAO orderTableIterator: orderTable) {
			PortfolioStock portfolioStock = new PortfolioStock();
			if(clientId.equals(orderTableIterator.getClientId())) {
				portfolioStock.setCode(orderTableIterator.getCode());
				portfolioStock.setQuantity(orderTableIterator.getQuantity());
				portfolioStock.setBuyPrice(orderTableIterator.getBuyPrice());
				portfolioStock.setCurrentValue(orderTableIterator.getBuyPrice().multiply(new BigDecimal(orderTableIterator.getQuantity())));
				portfolio.setPortfolioStockView(portfolioStock);
				portfolioList.add(portfolio);
			}
			else {
				throw new IllegalArgumentException("client does not have any portfolio");
			}
		}
		return portfolioList;
	}

}
