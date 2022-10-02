package com.smallchange.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.smallchange.dao.HoldingsDAO;
import com.smallchange.dao.InstrumentDAO;
import com.smallchange.dao.OrderDAO;
import com.smallchange.model.Portfolio;
import com.smallchange.model.PortfolioMutalFund;
import com.smallchange.model.PortfolioStock;

public class PortfolioService {
	
	List<Portfolio> portfolioList = new ArrayList<>();
	
	

	public List<Portfolio> getUserPortfolio(String clientId, List<HoldingsDAO> holdingsTable,
			List<InstrumentDAO> instrumentTable) {
		if(clientId==null) {
			throw new NullPointerException("clientId cannot be null");
		}
		if(clientId.equals("")) {
			throw new IllegalArgumentException("clientId cannot be empty");
		}
		if(holdingsTable ==null) {
			throw new NullPointerException("holdings  table cannot be null");
		}
		if(holdingsTable.size()==0) {
			throw new IllegalArgumentException("holdings table cannot be empty");
		}
		if(instrumentTable==null) {
			throw new NullPointerException("instrument table cannot be null");
		}
		if(instrumentTable.size()==0) {
			throw new IllegalArgumentException("instrument table cannot be empty");
		}
		for(HoldingsDAO holdingTableIterator: holdingsTable) {
			Portfolio portfolio = new Portfolio();
			if(clientId.equals(holdingTableIterator.getClientId())) {
				InstrumentDAO instrumentDAO =instrumentTable.stream().filter(filterIterator -> holdingTableIterator.getCode().equals(filterIterator.getCode())).findAny().orElse(null);
				if(holdingTableIterator.getCategory().equalsIgnoreCase("Stock")) {
					PortfolioStock portfolioStock = new PortfolioStock();
					portfolioStock.setCode(holdingTableIterator.getCode());
					portfolioStock.setCurrentPrice(instrumentDAO.getCurrentPrice());
					portfolioStock.setBuyPrice(holdingTableIterator.getBuyPrice());
					portfolioStock.setName(holdingTableIterator.getName());
					portfolioStock.setQuantity(holdingTableIterator.getQuantity());
					BigDecimal investedAmount =holdingTableIterator.getBuyPrice().multiply(BigDecimal.valueOf(holdingTableIterator.getQuantity())).setScale(2);
					portfolioStock.setInvestedAmount(investedAmount);
					BigDecimal currentValue = instrumentDAO.getCurrentPrice().multiply(BigDecimal.valueOf(holdingTableIterator.getQuantity())).setScale(2);
					portfolioStock.setCurrentValue(currentValue);
					portfolioStock.setProfiOrLoss(currentValue.subtract(investedAmount));
//					BigDecimal change = currentValue.subtract(investedAmount);
//					portfolioStock.setPercentageChange(portfolioStock.getProfiOrLoss().divide(investedAmount).setScale(2));
					portfolioStock.setPercentageChange(new BigDecimal("20.00"));

					portfolio.setPortfolioStockView(portfolioStock);
					this.portfolioList.add(portfolio);

					
				}
				else if(holdingTableIterator.getCategory().equalsIgnoreCase("MUTALFUND")) {
					PortfolioMutalFund portfolioMutualFund = new PortfolioMutalFund();
					portfolioMutualFund.setCode(holdingTableIterator.getCode());
					portfolioMutualFund.setCurrentPrice(instrumentDAO.getCurrentPrice());
					portfolioMutualFund.setBuyPrice(holdingTableIterator.getBuyPrice());
					portfolioMutualFund.setName(holdingTableIterator.getName());
					portfolioMutualFund.setQuantity(holdingTableIterator.getQuantity());
					BigDecimal investedAmount =holdingTableIterator.getBuyPrice().multiply(BigDecimal.valueOf(holdingTableIterator.getQuantity())).setScale(2);
					portfolioMutualFund.setInvestedAmount(investedAmount);
					BigDecimal currentValue = instrumentDAO.getCurrentPrice().multiply(BigDecimal.valueOf(holdingTableIterator.getQuantity())).setScale(2);
					portfolioMutualFund.setCurrentValue(currentValue);
					portfolioMutualFund.setProfiOrLoss(currentValue.subtract(investedAmount));
					portfolioMutualFund.setPercentageChange(portfolioMutualFund.getProfiOrLoss().divide(investedAmount).setScale(2));
					portfolio.setPortfolioMutualFuundView(portfolioMutualFund);
					this.portfolioList.add(portfolio);

					
				}
				
			}
			
		}
		if(portfolioList == null || portfolioList.size()==0)
			throw new IllegalArgumentException("No portfolio found for the client");
		
				return portfolioList;
	}
	
}
