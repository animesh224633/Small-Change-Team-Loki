package com.smallchange.integration;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smallchange.uimodel.Portfolio;


public interface PortfolioMapper {
	List<Portfolio> getUserPortfolio(
			@Param("clientId") String clientId
			);
	

}
