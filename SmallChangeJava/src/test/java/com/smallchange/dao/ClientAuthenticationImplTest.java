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

import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.TradeHistory;

class ClientAuthenticationImplTest {

	JdbcTemplate jdbcTemplate;
	DbTestUtils dbTestUtils;
	ClientAuthenticationDao dao;
	SimpleDataSource dataSource;
	Connection connection;
	
	
	TransactionManager tm;
	
	
	@BeforeEach
	void setUp() throws SQLException {
		dataSource = new SimpleDataSource();
		connection = dataSource.getConnection();
						
		// Start the transaction
		
		tm=new TransactionManager(dataSource);
		tm.startTransaction();
		
		dbTestUtils = new DbTestUtils(connection);		
		jdbcTemplate = dbTestUtils.initJdbcTemplate();
		
		
		dao=new ClientAuthenticationImpl(dataSource);
				
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
		ClientSendBackDetails tradehistory=dao.loginAuthenticationService("TEAMLOKI", "teamloki");
		assertEquals(tradehistory.getMessage(),"authentication successful");
	}

}
