package com.smallchange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.smallchange.uimodel.Portfolio;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
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

}
