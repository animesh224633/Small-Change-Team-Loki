package com.smallchange.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smallchange.uimodel.Portfolio;


class PortfolioDaoImplTest {
	JdbcTemplate jdbcTemplate;
	DbTestUtils dbTestUtils;
	PortfolioDao dao;
	SimpleDataSource dataSource;
	Connection connection;
	
	
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
		
		
	}

	@AfterEach
	void tearDown() throws SQLException {
		// Rollback the transaction
		tm.rollbackTransaction();
		// Shutdown the DataSource
		dataSource.shutdown();
	}

	@DisplayName("Should check whether we are getting all the trade")
	@Test
	void getAll_Client() {
		List<Portfolio> clientPortfolio = dao.getUserPortfolio(1);
		assertEquals(2,clientPortfolio.size());
	}


}
