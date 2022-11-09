package com.smallchange.restService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.smallchange.integration.SmallChangeWalletMyBatisDao;
import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.SmallChangeWalletAmount;
import com.smallchange.uimodel.TradeHistory;
import com.smallchange.uimodel.WalletUpdateDetails;
import com.smallchange.uimodel.WalletUpdateValues;

@WebMvcTest(SmallChangeWalletService.class)
class SmallChangeWalletServiceWebLayerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private SmallChangeWalletMyBatisDao mockDao;
	
	private static final List<BankAccountDetails> bankDetails = Arrays.asList(
			new BankAccountDetails("HDFC Bank",new BigDecimal("7776.990000000000000000000000000000000") ),
			new BankAccountDetails("ICICI Bank",new BigDecimal("45645444.60000000000000000000000000000000")),
			new BankAccountDetails("KOTAK Bank",new BigDecimal("456.60000000000000000000000000000000") )
	);
	
	@Test
	void testSmallChangeWalletAmount() throws Exception {
	    SmallChangeWalletAmount amount=new SmallChangeWalletAmount(new BigDecimal("3444.60000000000000000000000000000000"));
		when(mockDao.getUserSmallChangeWalletAmount("1"))
		.thenReturn(amount);
		
		
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/smallChangeWallet/1")
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	@Test
	void testgetBankAccountDetails() throws Exception {
		
		when(mockDao.getBankAccountDetails("1"))
		.thenReturn(bankDetails);
	
	mockMvc.perform(get("/smallChangeWallet/getAccount/1"))
	       .andExpect(status().isOk())
	       .andExpect(jsonPath("$.length()").value(3));
		
	}
	
//	@Test
//	void testwalletUpdateDetails() throws Exception {
//		WalletUpdateValues wallet=new WalletUpdateValues("1","HDFC Bank",new BigDecimal(10));
//		WalletUpdateDetails message=new WalletUpdateDetails("Success");
//		when(mockDao.updateSmallChangeWallet(wallet))
//		.thenReturn(message);
//		
//		mockMvc.perform(get("/smallChangeWallet/update"))
//	       .andExpect(status().isOk());
//	}

}
