package com.smallchange.restService;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.smallchange.integration.TradeHistoryMyBatisDao;
import com.smallchange.uimodel.TradeHistory;


@WebMvcTest(TradeHistoryService.class)
public class TradeHistoryServiceWebLayerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private TradeHistoryMyBatisDao mockDao;
	
	private static final List<TradeHistory> tradeHistory = Arrays.asList(
			new TradeHistory("AMAZON", "AMZN",10, "BUY",new BigDecimal("5694.70000000000000000000000000000000") , "STOCK"),
			new TradeHistory("LEAP MUTUAL FUND", "LMF",5, "BUY",new BigDecimal("232.60000000000000000000000000000000") , "MUTUALFUND")
	);
	
	@Test
	public void testQueryForTradeHistoryByClientId() throws Exception {
		when(mockDao.getTradeHistory("1"))
			.thenReturn(tradeHistory);
		
		mockMvc.perform(get("/tradeHistory/1"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.length()").value(2))
		       .andExpect(jsonPath("$[0].code").value("AMZN"))
		       .andExpect(jsonPath("$[1].code").value("LMF"));
	}
	
	@Test
	public void testQueryForAllTradeHistoryEmptyList() throws Exception {
		when(mockDao.getTradeHistory("1"))
			.thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/tradeHistory/1"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.length()").value(0));
	}
	
	@Test
	public void testQueryForAllTradeHistoryDaoException() throws Exception {
		when(mockDao.getTradeHistory("1"))
			.thenThrow(new RuntimeException("mock exception"));
		
		mockMvc.perform(get("/tradeHistory/1"))
		       .andExpect(status().isInternalServerError())
		       .andExpect(content().string(is(emptyOrNullString())));
	}
	
	@Test
	public void testQueryForTradeHistoryInvalidClientId() throws Exception {
		String id = "0";
		mockMvc.perform(get("/tradeHistory/" + id))
		.andExpect(status().isOk())
	       .andExpect(jsonPath("$.length()").value(0));
	}
}

