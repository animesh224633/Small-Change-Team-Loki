package com.smallchange.integration;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.integration.mapper.PortfolioMapper;
import com.smallchange.uimodel.Portfolio;


@Repository
public class PortfolioDaoMyBatisImpl implements PortfolioMyBatisDao{

	@Autowired
	private Logger logger;

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Override
	public List<Portfolio> getUserPortfolio(String clientId)  {
		if(clientId==null) {
			throw new NullPointerException("client id cannot be null");
		}
		
		System.out.println("Hello seneha" + portfolioMapper.getUserPortfolio("1").get(0).getPortfolioStockView().getName());
		logger.debug("enter");
		
		return portfolioMapper.getUserPortfolio(clientId);
	}
	
	

}
