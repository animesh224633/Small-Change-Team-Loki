package com.smallchange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.dao.DatabaseException;
import com.smallchange.uimodel.Portfolio;

@SpringBootTest
@Transactional
class PortfolioDaoMyBatisImplTest {
	
	@Autowired
	private PortfolioDaoMyBatisImpl dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@DisplayName("Should retrieve the portfolio of a client")
	@Test
	void getClientPortfolioById() {
		List<Portfolio> clientPortfolio = dao.getUserPortfolio("1");
		assertEquals(2,clientPortfolio.size());
	}
	
	
	
	
	
	@DisplayName("Should return 0 when client id is zero")
	@Test
	void getClientWithClientIdZero() {
		assertThrows(NullPointerException.class, () -> {
			dao.getUserPortfolio(null);
		});
	}
	@DisplayName("Should contain amazon when retrieving client 1's portfolio")
	@Test
	void client1ShouldConatinAmazon() {

		Portfolio client1 = new Portfolio( "AMAZON",  "AMZN", 1 , BigDecimal.valueOf(5694.70).setScale(2), BigDecimal.valueOf(100.80).setScale(2),
				BigDecimal.valueOf(5694.70).setScale(2), BigDecimal.valueOf(100.80).setScale(2),  BigDecimal.valueOf(-5593.90), BigDecimal.valueOf(-98.00).setScale(2));
		List<Portfolio> clientPortfolio = dao.getUserPortfolio("1");
		assertEquals(client1.getCode(),clientPortfolio.get(0).getCode());
		
		//assertTrue(clientPortfolio.contains(client1));
	}
	

}
