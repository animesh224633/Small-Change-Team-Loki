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

	@DisplayName("Should return success login")
	@Test
	void get_login() {
		ClientSendBackDetails successlogin=dao.loginAuthenticationService("TEAMLOKI", "teamloki");
		assertEquals(successlogin.getMessage(),"authentication successful");
	}
	
	@DisplayName("Should return wrong login")
	@Test
	void Wrong_login() {
		ClientSendBackDetails successlogin=dao.loginAuthenticationService("TEAMLOKIs", "teamloki");
		assertEquals(successlogin.getMessage(),"client mail or password incorrect");
	}

	@DisplayName("Should return success insert")
	@Test
	void insert_client() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService("Seneha", "seneha@gmail.com", "seneha1561");
		assertEquals(successlogin.getMessage(),"registered successfully");
	}
	
	@DisplayName("Should return client mail already exist")
	@Test
	void insert_client_alreadyPresent() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService("TEAMLOKI", "seneha@gmail.com", "teamloki");
		assertEquals(successlogin.getMessage(),"client mail already exists.");
	}
	
	@DisplayName("Should return null password")
	@Test
	void insert_client_withnullpassword() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService("TEAMLOKI", "seneha@gmail.com", null);
		assertEquals(successlogin.getMessage(),"Password cannot be empty or null");
	}
	
	@DisplayName("Should return null name")
	@Test
	void insert_client_withnullname() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService(null, "seneha@gmail.com", "seneha");
		assertEquals(successlogin.getMessage(),"Name cannot be empty or null");
	}
	
	@DisplayName("Should return null mail")
	@Test
	void insert_client_withnullmail() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService("TEAMLOKI", null, "seneha");
		assertEquals(successlogin.getMessage(),"Mail cannot be empty or null");
	}
	
	@DisplayName("Should return null empty")
	@Test
	void insert_client_withemptypassword() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService("TEAMLOKI", "seneha@gmail.com", "");
		assertEquals(successlogin.getMessage(),"Password cannot be empty or null");
	}
	
	@DisplayName("Should return empty name")
	@Test
	void insert_client_withemptyname() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService("", "seneha@gmail.com", "seneha");
		assertEquals(successlogin.getMessage(),"Name cannot be empty or null");
	}
	
	@DisplayName("Should return empty mail")
	@Test
	void insert_client_withemptymail() {
		ClientSendBackDetails successlogin=dao.registrationAuthenticationService("TEAMLOKI", "", "seneha");
		assertEquals(successlogin.getMessage(),"Mail cannot be empty or null");
	}
}
