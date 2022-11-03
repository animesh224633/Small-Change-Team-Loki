package com.smallchange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smallchange.dao.ClientAuthenticationImpl;
import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.PortfolioStock;

class ClientAauthentictionMyBatisImplTest {
	
	@Autowired
	private ClientAauthentictionMyBatisImpl dao;
	
	private ClientSendBackDetails clientSendBackDetails;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@DisplayName("Should retrieve the correct clientId")
	@Test
	void getClientPortfolioById() {
		
		assertEquals(2,dao.loginAuthenticationService("loki@gmail.com", "loki").getClientId());
	}
	
	
	
	
	
//	@DisplayName("Should return 0 when client id is zero")
//	@Test
//	void getClientWithClientIdZero() {
//		assertThrows(NullPointerException.class, () -> {
//			dao.getUserPortfolio(null);
//		});
//	}
//	@DisplayName("Should contain amazon when retrieving client 1's portfolio")
//	@Test
//	void client1ShouldConatinAmazon() {
//		Portfolio client1=null;
//
//		PortfolioStock amazon = new PortfolioStock( "AMAZON",  "AMZN", 1 , BigDecimal.valueOf(5694.70).setScale(2), BigDecimal.valueOf(100.80).setScale(2),
//				BigDecimal.valueOf(5694.70).setScale(2), BigDecimal.valueOf(100.80).setScale(2),  BigDecimal.valueOf(-5593.90), BigDecimal.valueOf(-98.00).setScale(2));
//		 client1=new Portfolio();
//		client1.setPortfolioStockView(amazon);
//		List<Portfolio> clientPortfolio = dao.getUserPortfolio("1");
//		System.out.println(clientPortfolio.get(0).getPortfolioStockView().getCode());
//		assertEquals(client1.getPortfolioStockView().getCode(),clientPortfolio.get(0).getPortfolioStockView().getCode());
//		
//		//assertTrue(clientPortfolio.contains(client1));
//	}

}
