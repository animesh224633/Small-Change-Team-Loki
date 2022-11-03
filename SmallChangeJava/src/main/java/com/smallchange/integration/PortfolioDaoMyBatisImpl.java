package com.smallchange.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.uimodel.Portfolio;


@Repository("PortfolioDao")
public class PortfolioDaoMyBatisImpl {

	@Autowired
	private Logger logger;

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	public List<Portfolio> getUserPortfolio(String clientId)  {
		if(clientId==null) {
			throw new NullPointerException("client id cannot be null");
		}
		
		System.out.println("Hello seneha" + portfolioMapper.getUserPortfolio("1").get(0).getPortfolioStockView().getName());
		logger.debug("enter");
		
		return portfolioMapper.getUserPortfolio(clientId);
	}
	
	

}
