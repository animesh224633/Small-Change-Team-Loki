package com.smallchange.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smallchange.uimodel.Portfolio;

@Mapper  
public interface PortfolioMapper {
	List<Portfolio> getUserPortfolio(
			@Param("clientId") String clientId
			);
	

}
