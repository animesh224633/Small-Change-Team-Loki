package com.smallchange.integration;

import java.util.List;

import com.smallchange.uimodel.Portfolio;

public interface PortfolioMyBatisDao {
	
	public List<Portfolio> getUserPortfolio(String clientId);

}
