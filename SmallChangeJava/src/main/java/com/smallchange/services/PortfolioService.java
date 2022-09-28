package com.smallchange.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smallchange.dao.InstrumentDAO;
import com.smallchange.dao.OrderDAO;
import com.smallchange.model.Portfolio;
import com.smallchange.model.PortfolioMutalFund;
import com.smallchange.model.PortfolioStock;

public class PortfolioService {
	
	List<Portfolio> portfolioList = new ArrayList<>();
	
	public List<Portfolio> getUserPortfolio(String clientId, List<OrderDAO> orderTable,
			List<InstrumentDAO> instrumentTable) {
		// Assuming that instrument category is stock; code needs to be modified further and check if 
		//category is stock then only add to portfoliostock otherwise add to portfoliomutualfund
		
		
		for(OrderDAO orderTableIterator: orderTable) {
			Portfolio portfolio = new Portfolio();
			if(clientId.equals(orderTableIterator.getClientId())) {
				InstrumentDAO instrumentDAO =instrumentTable.stream().filter(filterIterator -> orderTableIterator.getCode().equals(filterIterator.getCode())).findAny().orElse(null);
				int indexOfInstrumentCode = instrumentTable.indexOf(instrumentDAO);
				if(instrumentTable.get(indexOfInstrumentCode).getCategory().equals("Stock")) {
					PortfolioStock portfolioStock = new PortfolioStock();
					portfolioStock.setName(instrumentTable.get(indexOfInstrumentCode).getName());
					portfolioStock.setCode(orderTableIterator.getCode());
					portfolioStock.setQuantity(orderTableIterator.getQuantity());
					portfolioStock.setBuyPrice(orderTableIterator.getBuyPrice());	
					portfolioStock.setCurrentPrice(instrumentTable.get(indexOfInstrumentCode).getCurrentPrice());
					BigDecimal investedAmount = orderTableIterator.getBuyPrice().multiply(new BigDecimal(orderTableIterator.getQuantity()));
					portfolioStock.setInvestedAmount(investedAmount);
					BigDecimal currentValue = instrumentTable.get(indexOfInstrumentCode).getCurrentPrice().multiply(new BigDecimal(orderTableIterator.getQuantity()));
					portfolioStock.setCurrentValue(currentValue);
					portfolioStock.setProfiOrLoss(currentValue.subtract(investedAmount));
					portfolioStock.setPercentageChange((currentValue.subtract(investedAmount)).divide(investedAmount).setScale(2));
					portfolio.setPortfolioStockView(portfolioStock);
					portfolioList.add(portfolio);
				}
				else if(instrumentTable.get(indexOfInstrumentCode).getCategory().equals("MutualFund")) {
					PortfolioMutalFund portfolioMutualFund = new PortfolioMutalFund();
					portfolioMutualFund.setName(instrumentTable.get(indexOfInstrumentCode).getName());
					portfolioMutualFund.setCode(orderTableIterator.getCode());
					portfolioMutualFund.setQuantity(orderTableIterator.getQuantity());
					portfolioMutualFund.setBuyPrice(orderTableIterator.getBuyPrice());	
					portfolioMutualFund.setCurrentPrice(instrumentTable.get(indexOfInstrumentCode).getCurrentPrice());
					BigDecimal investedAmount = orderTableIterator.getBuyPrice().multiply(new BigDecimal(orderTableIterator.getQuantity()));
					portfolioMutualFund.setInvestedAmount(investedAmount);
					BigDecimal currentValue = instrumentTable.get(indexOfInstrumentCode).getCurrentPrice().multiply(new BigDecimal(orderTableIterator.getQuantity()));
					portfolioMutualFund.setCurrentValue(currentValue);
					portfolioMutualFund.setProfiOrLoss(currentValue.subtract(investedAmount));
					portfolioMutualFund.setPercentageChange((currentValue.subtract(investedAmount)).divide(investedAmount).setScale(2));
					portfolio.setPortfolioMutualFuundView(portfolioMutualFund);
					portfolioList.add(portfolio);
				}
				
			}
		}
		if(portfolioList == null)
			throw new IllegalArgumentException("No portfolio found for the client ");
		
		return portfolioList;
	}

}
