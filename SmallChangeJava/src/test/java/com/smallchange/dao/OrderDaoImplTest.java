package com.smallchange.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.SellInstrument;


class OrderDaoImplTest {

	private JdbcTemplate jdbcTemplate;
	private DbTestUtils dbTestUtils;
	private SimpleDataSource dataSource;
	private Connection connection;
	private TransactionManager transactionManager;
	private SellInstrument sell;
	private SellInstrument sell1;
	private SellInstrument sell2;
	private BuyInstrument buy;
	private BuyInstrument buy1;
	private BuyInstrument buy2;
	
	private OrderDaoImpl dao;
	@BeforeEach
	void setUp() throws Exception {
		
		dataSource = new SimpleDataSource();
		 dao= new OrderDaoImpl(dataSource);
		connection = dataSource.getConnection();
		dbTestUtils = new DbTestUtils(connection);		
		jdbcTemplate = dbTestUtils.initJdbcTemplate();
		transactionManager = new TransactionManager(dataSource);
		transactionManager.startTransaction();
		sell=new SellInstrument("1101","1","AMZN","AMAZON","STOCK",1,new BigDecimal("100.80"));
		sell1=new SellInstrument("1103","2","APL","APPLE","STOCK",1,new BigDecimal("121.30"));
		sell2=new SellInstrument("1102","1","LMF","LEAP MUTUAL FUND","MUTUALFUND",1,new BigDecimal("1141.70"));
		buy=new BuyInstrument("AMZN","AMAZON","STOCK",new BigDecimal("100.80"));
		
		buy1=new BuyInstrument("APL","APPLE","STOCK",new BigDecimal("121.30"));
		buy2=new BuyInstrument("SBIMF","SBI MUTUAL FUND","MUTUALFUND",new BigDecimal("111.70"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetSellInstrument_Success() {
		List<SellInstrument> allSellInstrument = dao.getSellTrade();
		assertEquals(3, allSellInstrument.size());
		assertTrue(allSellInstrument.contains(sell));
		assertTrue(allSellInstrument.contains(sell1));
		assertTrue(allSellInstrument.contains(sell2));
		
		
	}
	@Test
	void testGetBuyInstrument_Success() {
		List<BuyInstrument> allBuyInstrument = dao.getBuyTrade();
		assertEquals(4, allBuyInstrument.size());
		assertTrue(allBuyInstrument.contains(buy));
		assertTrue(allBuyInstrument.contains(buy1));
		assertTrue(allBuyInstrument.contains(buy2));
		
		
	}

}
