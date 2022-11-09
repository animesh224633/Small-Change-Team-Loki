package com.smallchange.integration;



import static org.junit.jupiter.api.Assertions.*;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;



import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.model.Client;
import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.BuyOrder;
import com.smallchange.uimodel.Order;
import com.smallchange.uimodel.SellInstrument;
import com.smallchange.uimodel.TradeHistory;





   //@ExtendWith(SpringExtension.class)
    //@ContextConfiguration("classpath:beans.xml")
@SpringBootTest
    @Transactional
    class OrderDaoMyBatisImplTest {
        
        SellInstrument sell=new SellInstrument("1101","1","AMZN","AMAZON","STOCK",1,new BigDecimal("100.80"));
         SellInstrument sell1=new SellInstrument("1103","2","APL","APPLE","STOCK",1,new BigDecimal("121.30"));
         SellInstrument sell2=new SellInstrument("1102","1","LMF","LEAP MUTUAL FUND","MUTUALFUND",1,new BigDecimal("1141.70"));
         BuyInstrument buy=new BuyInstrument("AMZN","AMAZON","STOCK",new BigDecimal("100.80"));
        
         BuyInstrument buy1=new BuyInstrument("APL","APPLE","STOCK",new BigDecimal("121.30"));
         BuyInstrument buy2=new BuyInstrument("SBIMF","SBI MUTUAL FUND","MUTUALFUND",new BigDecimal("111.70"));
        
         BuyOrder bo=new BuyOrder();
         
            Client clientDAO = new Client();
        @BeforeEach
        void setUp() {
            clientDAO.setClientSmallChangeWallet(new BigDecimal("4000.00"));
        clientDAO.setClientId("1");
        }
            
    
        @Autowired
        private OrderDaoMyBatisImpl dao;
        
        @Autowired
        private JdbcTemplate jdbcTemplate;
        @DisplayName("Get Sell Instrument Positive Test ")
        @Test
        void testGetSellInstrument_Success() {
List<SellInstrument> allSellInstrument = dao.getSellInstrument();
            System.out.println(allSellInstrument);
            assertEquals(3, allSellInstrument.size());
            assertFalse(allSellInstrument.contains(sell));
            assertTrue(allSellInstrument.contains(sell1));
            assertTrue(allSellInstrument.contains(sell2));
            //assertEquals()
            
        }
        @DisplayName(" Get Buy Trade Positive Test")
        @Test
        void testGetBuyInstrument_Success() {
            List<BuyInstrument> allBuyInstrument = dao.getBuyInstrument();
            assertEquals(4, allBuyInstrument.size());
            assertTrue(allBuyInstrument.contains(buy));
            assertTrue(allBuyInstrument.contains(buy1));
            assertTrue(allBuyInstrument.contains(buy2));
                
        }
        @DisplayName("Put Buy Trade Positive Test for holdings insert")
        @Test
        void putBuyTradeTest_Success1() throws InsufficientFundsException {
        
            
                bo.setCode("SBIMF");
                bo.setOrderId(UUID.randomUUID().toString());
                bo.setBuyPrice(new BigDecimal("240.00"));
                bo.setQuantity(6);
                bo.setDirection("buy");
                bo.setClientId("1");
                bo.setTimestamp(LocalDate.now());
                clientDAO.setClientId("1");
                clientDAO.setClientMail("loki@gmail.com");
                clientDAO.setClientName("loki");
                clientDAO.setClientSmallChangeWallet(new BigDecimal("1000.00"));
                clientDAO.setPassword("loki123");
                
                assertTrue(dao.putBuyTrade(bo));
        }
        @DisplayName("Put Buy Trade Positive Test for holdings update")
        @Test
        void putBuyTradeTest_Success2() throws InsufficientFundsException {
        
            
                bo.setCode("APL");
                bo.setOrderId(UUID.randomUUID().toString());
                bo.setBuyPrice(new BigDecimal("240.00"));
                bo.setQuantity(6);
                bo.setDirection("buy");
                bo.setClientId("1");
                bo.setTimestamp(LocalDate.now());
                clientDAO.setClientId("1");
                clientDAO.setClientMail("loki@gmail.com");
                clientDAO.setClientName("loki");
                clientDAO.setClientSmallChangeWallet(new BigDecimal("1000.00"));
                clientDAO.setPassword("loki123");
                
                assertTrue(dao.putBuyTrade(bo));
        }
    
        
        



}
