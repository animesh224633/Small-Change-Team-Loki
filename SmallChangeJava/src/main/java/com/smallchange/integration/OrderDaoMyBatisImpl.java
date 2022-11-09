package com.smallchange.integration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.dao.DatabaseException;
import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.integration.mapper.OrderMapper;
import com.smallchange.model.Holdings;
import com.smallchange.model.Instrument;
import com.smallchange.model.Orders;
import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.BuyOrder;
import com.smallchange.uimodel.Order;
import com.smallchange.uimodel.SellInstrument;
import com.smallchange.uimodel.SellOrder;
import com.smallchange.uimodel.TradeHistory;
@Repository
public class OrderDaoMyBatisImpl {



		@Autowired
		private Logger logger;

		@Autowired
		private OrderMapper orderMapper;
		
		public List<SellInstrument> getSellInstrument(String clientId) {
			
			System.out.println("Entered getSellInstrument method");
			logger.debug("enter");
		
			return orderMapper.getSellInstrument(clientId);
		}
public List<BuyInstrument> getBuyInstrument() {
			
			System.out.println("Entered getBuyInstrument method");
			logger.debug("enter");
		
			return orderMapper.getBuyInstrument();
		}
public boolean putBuyTrade(BuyOrder bo) throws InsufficientFundsException {

	 Objects.requireNonNull(bo);
	 String iou = null;
		boolean flag=false;
		float wallet_amount=orderMapper.getClientWallet(bo.getClientId())
				;
		
				
					
					 if(bo.getClientId()==null) {
							throw new NullPointerException("clientId cannot be null");
						}
						if(bo.getClientId().equals("")) {
							throw new IllegalArgumentException("clientId cannot be empty");
						}
					
					
					//if(bo.getClientId().equals(cli))
						BigDecimal wallet=new BigDecimal(wallet_amount).setScale(2,RoundingMode.HALF_DOWN);
						
						if((bo.getBuyPrice().multiply(new BigDecimal(bo.getQuantity()))).compareTo(wallet) >0 ){
							throw new InsufficientFundsException("You do not have enough wallet balance money to execute this buy trade");
						}

						BigDecimal oldWalletMoney=wallet;
						BigDecimal newWalletMoney=(oldWalletMoney).subtract(bo.getBuyPrice().multiply(new BigDecimal(bo.getQuantity())));
					float newWallet=newWalletMoney.floatValue();
						System.out.println("Old Wallet amount:"+oldWalletMoney);
					orderMapper.updateClientWalletBuy(bo, newWallet);
						
						
						System.out.println("New Wallet amount:"+newWalletMoney);
			

		/*if(!flag) {
			throw new IllegalArgumentException("Client doesn't exist");
		}*/
						
						//SellInstrument ho
						String code=bo.getCode();
						List<SellInstrument> holdings=getSellInstrument(bo.getClientId());
						BigDecimal holdingPrice=null;
						BigDecimal holdingQuantity=null;
						for(SellInstrument holding : holdings) {
							
							System.out.println(holding.getCode());
							System.out.println(code);
							if(holding.getCode().equals(code) && holding.getClientId().equals(bo.getClientId())) {
								
								iou="u";
								holdingPrice=orderMapper.getHoldingBuyPrice(bo);
								holdingQuantity=BigDecimal.valueOf(holding.getQuantity());
								
								break;
							}
							else {
								
								iou="i";
								
							}
						}
						if(iou=="u") {
							int orderQuantity=bo.getQuantity();
							BigDecimal newBuySum=bo.getBuyPrice().multiply(BigDecimal.valueOf(bo.getQuantity()));
							BigDecimal oldBuySum=holdingQuantity.multiply(holdingPrice);
							BigDecimal quntitySum=BigDecimal.valueOf(bo.getQuantity()).add(holdingQuantity);
							BigDecimal updatedBuyprice=newBuySum.add(oldBuySum).divide(quntitySum,2, RoundingMode.HALF_DOWN);
							bo.setBuyPrice(updatedBuyprice);
							bo.setQuantity(quntitySum.intValue());
							System.out.println("newbuysum "+newBuySum);
							System.out.println("holdingprice "+holdingPrice);
							System.out.println("oldBuySum "+oldBuySum);
							System.out.println("quantitySum "+quntitySum);
							System.out.println("updatedBuyprice "+updatedBuyprice);
							bo.setOrderId(UUID.randomUUID().toString());
							bo.setTimestamp(LocalDate.now());




							orderMapper.putBuyTradeHoldingsUpdate(bo);
							bo.setBuyPrice(newBuySum);
							bo.setQuantity(orderQuantity);
							flag=true;
							System.out.println("executing update!");
						}
						else if( iou=="i") {
							BigDecimal newBuySum=bo.getBuyPrice().multiply(BigDecimal.valueOf(bo.getQuantity()));

							bo.setHolding_id(UUID.randomUUID().toString());
							bo.setOrderId(UUID.randomUUID().toString());
							bo.setTimestamp(LocalDate.now());

							orderMapper.putBuyTradeHoldingsInsert(bo);
							bo.setBuyPrice(newBuySum);

							flag=true;
							System.out.println("executing insert!");
						}
						
		
	 return orderMapper.putBuyTradeOrder(bo) == 1 && flag;
	}







public boolean putSellTrade(SellOrder bo) throws InsufficientFundsException {

	 Objects.requireNonNull(bo);
	 String iou = null;
		boolean flag=false;
		float wallet_amount=orderMapper.getClientWallet(bo.getClientId())
				;
		
				
					
					 if(bo.getClientId()==null) {
							throw new NullPointerException("clientId cannot be null");
						}
						if(bo.getClientId().equals("")) {
							throw new IllegalArgumentException("clientId cannot be empty");
						}
						
					
					
					//if(bo.getClientId().equals(cli))
						BigDecimal wallet=new BigDecimal(wallet_amount).setScale(2,RoundingMode.HALF_DOWN);
						
						

						BigDecimal oldWalletMoney=wallet;
						BigDecimal newWalletMoney=(oldWalletMoney).add(bo.getSellPrice().multiply(new BigDecimal(bo.getQuantity())));
					float newWallet=newWalletMoney.floatValue();
						System.out.println("Old Wallet amount:"+oldWalletMoney);
					orderMapper.updateClientWalletSell(bo, newWallet);
						
						
						System.out.println("New Wallet amount:"+newWalletMoney);
			

		
						
						//SellInstrument ho
						String code=bo.getCode();
						List<SellInstrument> holdings=getSellInstrument(bo.getClientId());
						//BigDecimal holdingPrice=null;
						BigDecimal holdingQuantity=null;
						for(SellInstrument holding : holdings) {
							
							
							System.out.println(holding.getCode());
							System.out.println(code);
							if(holding.getCode().equals(code)&& holding.getClientId().equals(bo.getClientId())) {
								
								iou="u";
								//holdingPrice=holding.getCurrentPrice();
								if((bo.getQuantity()>(holding.getQuantity()  ))){
									System.out.println("deddedede");
									throw new InsufficientFundsException("You do not have enough holdings to execute this sell trade");
								}
								holdingQuantity=BigDecimal.valueOf(holding.getQuantity());
								
								break;
							}
							else {
								
								iou="i";
								
							}
						}
						if(iou=="u") {
							//BigDecimal newBuySum=bo.getSellPrice().multiply(BigDecimal.valueOf(bo.getQuantity()));
							//BigDecimal oldBuySum=holdingQuantity.multiply(holdingPrice);
							int orderQuantity=bo.getQuantity();
							BigDecimal newBuySum=bo.getSellPrice().multiply(BigDecimal.valueOf(bo.getQuantity()));

							BigDecimal quntitySum=holdingQuantity.subtract(BigDecimal.valueOf(bo.getQuantity()));
							//BigDecimal updatedBuyprice=newBuySum.add(oldBuySum).divide(quntitySum);
							//bo.setBuyPrice(updatedBuyprice);
							bo.setQuantity(quntitySum.intValue());
							bo.setOrderId(UUID.randomUUID().toString());
							bo.setTimestamp(LocalDate.now());
							orderMapper.putSellTradeHoldingsUpdate(bo);
							bo.setSellPrice(newBuySum);

							bo.setQuantity(orderQuantity);
							flag=true;
							System.out.println("executing update!");
						}
						else if( iou=="i") {
							//orderMapper.putSellTradeHoldingsInsert(bo);
							flag=false;
							System.out.println("executing insert!");
						}
						
		
	 return orderMapper.putSellTradeOrder(bo) == 1 && flag;
	}
}