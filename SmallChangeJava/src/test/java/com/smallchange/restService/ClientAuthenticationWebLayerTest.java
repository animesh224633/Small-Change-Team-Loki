//package com.smallchange.restService;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.math.BigDecimal;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.smallchange.integration.ClientAuthenticationMyBatisDao;
//import com.smallchange.uimodel.ClientSendBackDetails;
//import com.smallchange.uimodel.LoginClientDetails;
//import com.smallchange.uimodel.SmallChangeWalletAmount;
//
//@WebMvcTest(ClientAuthenticationService.class)
//class ClientAuthenticationWebLayerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//	@MockBean
//	private ClientAuthenticationMyBatisDao mockDao;
//	
//	@Test
//	void testClientLogin() throws Exception {
//		LoginClientDetails client=new LoginClientDetails();
//		client.setClientMail("teamloki@gmail.com");
//		client.setPassword("teamloki");
//		ClientSendBackDetails clientDetails=new ClientSendBackDetails("1","Fetched successfully");
//		when(mockDao.loginAuthenticationService(client))
//		.thenReturn(clientDetails);
//		
//		
//		mockMvc.perform( MockMvcRequestBuilders
//			      .get("/clientAuthentication/login")
//			      .accept(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk());
//	}
//
//}
