package com.smallchange.integration;

import java.util.List;

import com.smallchange.uimodel.Portfolio;


public interface PortfolioMapper {
	List<Portfolio> getUserPortfolio(String clientId);

}
