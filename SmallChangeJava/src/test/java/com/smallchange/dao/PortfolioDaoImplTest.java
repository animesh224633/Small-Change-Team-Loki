package com.smallchange.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.PortfolioStock;
import com.smallchange.uimodel.TradeHistory;


class PortfolioDaoImplTest {
	JdbcTemplate jdbcTemplate;
	DbTestUtils dbTestUtils;
	PortfolioDao dao;
	SimpleDataSource dataSource;
	Connection connection;
	Portfolio client1=null;
	
	
	
	TransactionManager tm;
	

	@BeforeEach
	void setUp() throws Exception {
		dataSource = new SimpleDataSource();
		connection = dataSource.getConnection();
						
		// Start the transaction
		
		tm=new TransactionManager(dataSource);
		tm.startTransaction();
		
		dbTestUtils = new DbTestUtils(connection);		
		jdbcTemplate = dbTestUtils.initJdbcTemplate();
		dao = new PortfolioDaoImpl(dataSource);
		PortfolioStock amazon = new PortfolioStock( "AMAZON",  "AMZN", 1 , BigDecimal.valueOf(5694.70).setScale(2), BigDecimal.valueOf(100.80).setScale(2),
				BigDecimal.valueOf(5694.70).setScale(2), BigDecimal.valueOf(100.80).setScale(2),  BigDecimal.valueOf(-5593.90), BigDecimal.valueOf(-98.00).setScale(2));
		 client1=new Portfolio();
		client1.setPortfolioStockView(amazon);
		
	}

	@AfterEach
	void tearDown() throws SQLException {
		// Rollback the transaction
		tm.rollbackTransaction();
		// Shutdown the DataSource
		dataSource.shutdown();
	}

	@DisplayName("Should retrieve the portfolio of a client")
	@Test
	void getClientPortfolioById() {
		List<Portfolio> clientPortfolio = dao.getUserPortfolio(1);
		assertEquals(2,clientPortfolio.size());
	}
	
	
	@DisplayName("Should return 0 when client id is zero")
	@Test
	void getClientWithClientIdZero() {
		assertThrows(DatabaseException.class, () -> {
			dao.getUserPortfolio(0);
		});
	}
	/*@DisplayName("Should contain amazon when retrieving client 1's portfolio")
	@Test
	void client1ShouldConatinAmazon() {
		List<Portfolio> clientPortfolio = dao.getUserPortfolio(1);
		System.out.println(clientPortfolio.get(0).getPortfolioStockView().getCode());
		assertEquals(client1.getPortfolioStockView(),clientPortfolio.get(0).getPortfolioStockView());
		
		//assertTrue(clientPortfolio.contains(client1));
	}*/
	
	


}
