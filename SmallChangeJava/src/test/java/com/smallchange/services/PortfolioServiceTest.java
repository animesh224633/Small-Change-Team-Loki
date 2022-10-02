package com.smallchange.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.smallchange.dao.HoldingsDAO;
import com.smallchange.dao.InstrumentDAO;
import com.smallchange.model.Portfolio;

class PortfolioServiceTest {
	
	private List<HoldingsDAO> holdingList;
	private List<InstrumentDAO> instrumentDAOList;
	private InstrumentDAO instrumentDAO;
	private InstrumentDAO instrumentDAO2;
	private InstrumentDAO instrumentDAO3;
	private InstrumentDAO instrumentDAO4;
	private PortfolioService portfolioService;

	@BeforeEach
	void setup() {
		holdingList = new ArrayList<>();
		instrumentDAOList = new ArrayList<>();
		instrumentDAO = new InstrumentDAO();
		instrumentDAO.setCode("AMZN");
		instrumentDAO.setName("Amazon");
		instrumentDAO.setCurrentPrice(new BigDecimal("100.00"));
		instrumentDAO.setCategory("Stock");
		instrumentDAOList.add(instrumentDAO);
		instrumentDAO2 = new InstrumentDAO();
		instrumentDAO2.setCode("MSFT");
		instrumentDAO2.setName("Microsoft");
		instrumentDAO2.setCurrentPrice(new BigDecimal("50.00"));
		instrumentDAO2.setCategory("Stock");
		instrumentDAOList.add(instrumentDAO2);
		instrumentDAO3 = new InstrumentDAO();
		instrumentDAO3.setCode("TSLA");
		instrumentDAO3.setName("Tesla");
		instrumentDAO3.setCurrentPrice(new BigDecimal("5000.00"));
		instrumentDAO3.setCategory("Stock");
		instrumentDAOList.add(instrumentDAO3);
		instrumentDAO4 = new InstrumentDAO();
		instrumentDAO4.setCode("SBIMF");
		instrumentDAO4.setName("SBI REGULAR GROWTH MUTUAL FUND");
		instrumentDAO4.setCurrentPrice(new BigDecimal("7000.00"));
		instrumentDAO4.setCategory("MUTUALFUND");
		instrumentDAOList.add(instrumentDAO4);
		InstrumentDAO instrumentDAO5 = new InstrumentDAO();
		instrumentDAO5.setCode("WPRO");
		instrumentDAO5.setName("wIPRO");
		instrumentDAO5.setCurrentPrice(new BigDecimal("5000.00"));
		instrumentDAO5.setCategory("Stock");
		instrumentDAOList.add(instrumentDAO5);
		HoldingsDAO stockHolding1= new HoldingsDAO();
		stockHolding1.setName("Amazon");
		stockHolding1.setCode("AMZN");
		stockHolding1.setQuantity(100);
		stockHolding1.setHoldingId("dfhegqwehjfvDGWERRHYTRHJDBGCFHgqerjybvgui");
		stockHolding1.setBuyPrice(new BigDecimal("80.00"));
		stockHolding1.setCategory("Stock");
		stockHolding1.setClientId("loki");
		holdingList.add(stockHolding1);
		HoldingsDAO stockHolding2= new HoldingsDAO();
		stockHolding2.setName("Microsoft");
		stockHolding2.setCode("MSFT");
		stockHolding2.setQuantity(165);
		stockHolding2.setHoldingId("dfhegqwehjfFHJDFHDGFHTRvgqerjybvgui");
		stockHolding2.setBuyPrice(new BigDecimal("150.00"));
		stockHolding2.setCategory("Stock");
		stockHolding2.setClientId("loki");
		holdingList.add(stockHolding2);
		HoldingsDAO stockHolding3= new HoldingsDAO();
		stockHolding3.setName("Tesla");
		stockHolding3.setCode("TSLA");
		stockHolding3.setQuantity(214);
		stockHolding3.setHoldingId("dfhegqwehjfvgqeDFGDGDRGERGERGRGERGERGERGEGrjybvgui");
		stockHolding3.setBuyPrice(new BigDecimal("4000.00"));
		stockHolding3.setCategory("Stock");
		stockHolding3.setClientId("loki");
		holdingList.add(stockHolding3);
		HoldingsDAO mutualFundHolding =new  HoldingsDAO();
		mutualFundHolding.setName("SBI REGULAR GROWTH MUTUAL FUND");
		mutualFundHolding.setCode("SBIMF");
		mutualFundHolding.setQuantity(54);
		mutualFundHolding.setHoldingId("dfhegqwfdefwewefwefwfwDFGDFGDGehjfvDGWERRHYTRHJDBGCFHgqerjybvgui");
		mutualFundHolding.setBuyPrice(new BigDecimal("80.00"));
		mutualFundHolding.setCategory("MUTALFUND");
		mutualFundHolding.setClientId("loki");
		holdingList.add(mutualFundHolding);

		
		portfolioService = new PortfolioService();
	}
	
	@Test
	void checkPortfolioLength() {
		List<Portfolio> portfolioList = portfolioService.getUserPortfolio("loki", holdingList, instrumentDAOList);

		assertEquals(4,portfolioList.size());
		
	}
	
	@Test
	void checkNumberOfStocks() {
		List<Portfolio> portfolioList = portfolioService.getUserPortfolio("loki", holdingList, instrumentDAOList);
		int count=0;
		for (Portfolio portfolioIterator:portfolioList) {
			if(portfolioIterator.getPortfolioStockView()!=null) {
				count++;
			}
		}
		assertEquals(3,count);
	}
	@Test
	void checkNumberOfMutualFunds() {
		List<Portfolio> portfolioList = portfolioService.getUserPortfolio("loki", holdingList, instrumentDAOList);
		int count=0;
		for (Portfolio portfolioIterator:portfolioList) {
			if(portfolioIterator.getPortfolioMutualFuundView()!=null) {
				count++;
			}
		}
		assertEquals(1,count);
	}
	@Test
	void addStock() {
		HoldingsDAO stockHolding5= new HoldingsDAO();
		stockHolding5.setName("Wipro");
		stockHolding5.setCode("WPRO");
		stockHolding5.setQuantity(4);
		stockHolding5.setHoldingId("dfhegqwehjfvgqeDFGDghdrtsdbtdgrtsdgtdGDRGERGERGRGERGERGERGEGrjybvgui");
		stockHolding5.setBuyPrice(new BigDecimal("45.00"));
		stockHolding5.setCategory("Stock");
		stockHolding5.setClientId("loki");
		holdingList.add(stockHolding5);
		List<Portfolio> portfolioList = portfolioService.getUserPortfolio("loki", holdingList, instrumentDAOList);

		assertEquals(5,portfolioList.size());
		holdingList.remove(stockHolding5);
		
	}
	@Test
	void checkTeslaStockHolding() {
		List<Portfolio> portfolioList = portfolioService.getUserPortfolio("loki", holdingList, instrumentDAOList);
		for (Portfolio portfolioIterator:portfolioList) {
			if(portfolioIterator.getPortfolioStockView().getCode().equals("TSLA")) {
				assertEquals("Tesla",portfolioIterator.getPortfolioStockView().getName());
				assertEquals(BigDecimal.valueOf(4000).setScale(2), portfolioIterator.getPortfolioStockView().getBuyPrice());
				assertEquals(BigDecimal.valueOf(5000).setScale(2),portfolioIterator.getPortfolioStockView().getCurrentPrice());
				assertEquals(BigDecimal.valueOf(1070000).setScale(2),portfolioIterator.getPortfolioStockView().getCurrentValue());
				assertEquals(BigDecimal.valueOf(856000).setScale(2),portfolioIterator.getPortfolioStockView().getInvestedAmount());
				assertEquals(BigDecimal.valueOf(20).setScale(2),portfolioIterator.getPortfolioStockView().getPercentageChange());
				assertEquals(BigDecimal.valueOf(214000).setScale(2),portfolioIterator.getPortfolioStockView().getProfiOrLoss());
				assertEquals(Integer.valueOf(214),portfolioIterator.getPortfolioStockView().getQuantity());
			}
		}
	}
	@Test
	void checkSbiMutualFund() {
		
	}
	@Test
	void nullPortfolioList(){
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			portfolioService.getUserPortfolio("lokfffi1", holdingList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "No portfolio found for the client");
	}
	
	void testnoexistingclientId() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			portfolioService.getUserPortfolio("loki1", holdingList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "No portfolio found for the client");
	}
	
	@DisplayName("should throw error when client id is null")
	@Test
	void testNullCLientId() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			portfolioService.getUserPortfolio(null, holdingList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "clientId cannot be null");
	}
	
	@DisplayName("should throw error when client id is empty")
	@Test
	void testEmptyCLientId() throws Exception{
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			portfolioService.getUserPortfolio("", holdingList, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "clientId cannot be empty");
	}
	
	@DisplayName("should throw error when holdings table is null")
	@Test
	void testNullOrderTable() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			portfolioService.getUserPortfolio("loki", null, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "holdings  table cannot be null");
	}
	
	
	@DisplayName("should throw error when instrument table is null")
	@Test
	void testNullInstrumentTable() throws Exception{
		NullPointerException e=assertThrows(NullPointerException.class, ()->{
			portfolioService.getUserPortfolio("loki", holdingList, null);
		});
		assertEquals(e.getMessage(), "instrument table cannot be null");
	}
	
	@DisplayName("should throw error when holdings table is empty")
	@Test
	void testEmptyOrderTable() throws Exception{
		List<HoldingsDAO> emptyHolding=new ArrayList<>();
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			portfolioService.getUserPortfolio("loki", emptyHolding, instrumentDAOList);
		});
		assertEquals(e.getMessage(), "holdings table cannot be empty");
	}
	
	@DisplayName("should throw error when instrument table is empty")
	@Test
	void testEmptyInstrumentTable() throws Exception{
		List<InstrumentDAO> instruments=new ArrayList<>();
		IllegalArgumentException e=assertThrows(IllegalArgumentException.class, ()->{
			portfolioService.getUserPortfolio("loki", holdingList, instruments);
		});
		assertEquals(e.getMessage(), "instrument table cannot be empty");
	}
	

}
