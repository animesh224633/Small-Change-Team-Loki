package com.smallchange.integration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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
import com.smallchange.uimodel.TradeHistory;
@Repository
public class OrderDaoMyBatisImpl {



		@Autowired
		private Logger logger;

		@Autowired
		private OrderMapper orderMapper;
		
		public List<SellInstrument> getSellInstrument() {
			
			System.out.println("Entered getSellInstrument method");
			logger.debug("enter");
		
			return orderMapper.getSellInstrument();
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
					orderMapper.updateClientWallet(bo, newWallet);
						
						
						System.out.println("New Wallet amount:"+newWalletMoney);
			

		/*if(!flag) {
			throw new IllegalArgumentException("Client doesn't exist");
		}*/
						
						//SellInstrument ho
						String code=bo.getCode();
						List<SellInstrument> holdings=getSellInstrument();
						for(SellInstrument holding : holdings) {
							System.out.println(holding.getCode());
							System.out.println(code);
							if(holding.getCode().equals(code)) {
								
								iou="u";
								break;
							}
							else {
								
								iou="i";
								
							}
						}
						if(iou=="u") {
							orderMapper.putBuyTradeHoldingsUpdate(bo);
							flag=true;
							System.out.println("executing update!");
						}
						else if( iou=="i") {
							orderMapper.putBuyTradeHoldingsInsert(bo);
							flag=true;
							System.out.println("executing insert!");
						}
						
		
	 return orderMapper.putBuyTradeOrder(bo) == 1 && flag;
	}
	

}