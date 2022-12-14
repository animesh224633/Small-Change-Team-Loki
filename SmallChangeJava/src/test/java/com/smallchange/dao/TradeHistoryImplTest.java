//package com.smallchange.dao;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.smallchange.uimodel.TradeHistory;
//
//
//
//
//class TradeHistoryImplTest {
//	
//	JdbcTemplate jdbcTemplate;
//	DbTestUtils dbTestUtils;
//	TradeHistoryDao dao;
//	SimpleDataSource dataSource;
//	Connection connection;
//	
//	
//	TransactionManager tm;
//	
//	
//	@BeforeEach
//	void setUp() throws SQLException {
//		dataSource = new SimpleDataSource();
//		connection = dataSource.getConnection();
//						
//		// Start the transaction
//		
//		tm=new TransactionManager(dataSource);
//		tm.startTransaction();
//		
//		dbTestUtils = new DbTestUtils(connection);		
//		jdbcTemplate = dbTestUtils.initJdbcTemplate();
//		
//		
//		dao=new TradeHistoryImpl(dataSource);
//				
//	}
//
//	@AfterEach
//	void tearDown() throws SQLException {
//		// Rollback the transaction
//		tm.rollbackTransaction();
//		// Shutdown the DataSource
//		dataSource.shutdown();
//	}
//
//	@DisplayName("Should check whether we are getting all the trade")
//	@Test
//	void getAll_Client() {
//		List<TradeHistory> tradehistory=dao.getTradeHistory("1");
//		assertEquals(tradehistory.size(),2);
//	}
//	
//	@DisplayName("Should return 0 when client id is null")
//	@Test
//	void getclient_null() {
//		List<TradeHistory> tradehistory=dao.getTradeHistory(null);
//		assertEquals(tradehistory.size(),0);
//	}
//
//	
//}
