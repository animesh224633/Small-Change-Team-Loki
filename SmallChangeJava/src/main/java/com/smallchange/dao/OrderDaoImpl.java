package com.smallchange.dao;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import com.smallchange.exception.InsufficientFundsException;
import com.smallchange.model.Client;
import com.smallchange.model.Holdings;
import com.smallchange.model.Instrument;
import com.smallchange.model.Orders;
import com.smallchange.uimodel.BuyInstrument;
import com.smallchange.uimodel.SellInstrument;

public class OrderDaoImpl implements OrderDao {
	
	
	private DataSource datasource;

	public OrderDaoImpl(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public List<SellInstrument> getSellTrade() {
		String sql="""
			SELECT h.holding_id,
     h.client_id,
     h.code,
     i.name,
     i.category,
     h.quantity,
     i.current_price
     FROM holdings h join instrument i on h.code=i.code
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			List<SellInstrument> sell = handleSellResults(rs);
			return sell;
		} 
		catch (SQLException e) {
			String msg = "Cannot execute query in getCars";
			//logger.error(msg + ": " + e);
			throw new DatabaseException(msg, e);
		}
}

	private List<SellInstrument> handleSellResults(ResultSet rs) throws SQLException {
		List<SellInstrument>sells = new ArrayList<>();
		while (rs.next()) {
		
			String holdingId = rs.getString("holding_id");
			String clientId = rs.getString("client_id");
			String code= rs.getString("code");
			String name= rs.getString("name");
			String category= rs.getString("category");
			int quantity=rs.getInt("quantity");
			Float current_price = rs.getFloat("current_price");
			BigDecimal current=new BigDecimal(current_price).setScale(2,RoundingMode.HALF_DOWN);
			
		SellInstrument sell = new SellInstrument(holdingId,clientId,code,name,category,quantity,current);
		sells.add(sell);	
		
		
	}
		System.out.println(sells);
		return sells;
	}
	
	@Override
	public List<BuyInstrument> getBuyTrade() {
			
		String sql="""
				
       select * 
        from 
      instrument
				"""
		;
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			List<BuyInstrument> buy = handleBuyResults(rs);
			return buy;
		} 
		catch (SQLException e) {
			String msg = "Cannot execute query in getCars";
			//logger.error(msg + ": " + e);
			throw new DatabaseException(msg, e);
		}
}

	private List<BuyInstrument> handleBuyResults(ResultSet rs) throws SQLException {
		List<BuyInstrument>buyList = new ArrayList<>();
		while (rs.next()) {
		
		
			String code= rs.getString("code");
			Float current_price = rs.getFloat("current_price");
			String name= rs.getString("name");
			String category= rs.getString("category");
			
			BigDecimal current=new BigDecimal(current_price).setScale(2,RoundingMode.HALF_DOWN);
			
		BuyInstrument buy = new BuyInstrument(code,name,category,current);
		buyList.add(buy);	
		
		
	}
		System.out.println(buyList);
		return buyList;
	}

	

	@Override
	public void putBuyTrade(List<Orders> orderTable, List<Instrument> instrumentTable,Orders bo,List<Holdings> holdingsTable) throws InsufficientFundsException {
	

	Objects.requireNonNull(bo);
	try (Connection conn = datasource.getConnection()) {
		putBuyTrade(conn,  orderTable, instrumentTable, bo, holdingsTable);
		
	} 
	catch (SQLException e) {
		String msg = "Cannot execute putBuyTrade for " + bo;
	//	logger.error(msg + ": " + e);
		throw new DatabaseException(msg, e);

	}
	}
	
 public void putBuyTrade(Connection conn, List<Orders> orderTable, List<Instrument> instrumentTable,Orders bo,List<Holdings> holdingsTable) throws SQLException, InsufficientFundsException {
	 String sql="""
				Insert into orders (buy_price,client_id,code,direction,order_id,quantity,timestamp) Values (?,?,?,?,?,?,?)
				"""
			;
	 String sqlw="""
	 		Update client set client_smallchange_wallet =?
	 		"""
   ;
	 Objects.requireNonNull(bo);
	
		if(bo==null) {
			throw new NullPointerException("Order cannot be null");
		}
		boolean flag=false;
		String sqlclient="""
				 select client_smallchange_wallet from client where client_id=?
				"""
				;
		
				try (Connection conn1 = datasource.getConnection();
						PreparedStatement stmt = conn1.prepareStatement(sqlclient)) {
					stmt.setString(1, bo.getClientId());
					
					 if(bo.getClientId()==null) {
							throw new NullPointerException("clientId cannot be null");
						}
						if(bo.getClientId().equals("")) {
							throw new IllegalArgumentException("clientId cannot be empty");
						}
					
					ResultSet rs = stmt.executeQuery();
					
						Float wallet_amount = rs.getFloat("client_smallchange_wallet");
						BigDecimal wallet=new BigDecimal(wallet_amount).setScale(2,RoundingMode.HALF_DOWN);
						
						if((bo.getBuyPrice().multiply(new BigDecimal(bo.getQuantity()))).compareTo(wallet) >0 ){
							throw new InsufficientFundsException("You do not have enough wallet balance money to execute this buy trade");
						}

						BigDecimal oldWalletMoney=wallet;
						BigDecimal newWalletMoney=(oldWalletMoney).subtract(bo.getBuyPrice().multiply(new BigDecimal(bo.getQuantity())));
					
						try (PreparedStatement stmt1 = conn.prepareStatement(sqlw)) {
						stmt1.setBigDecimal(1, newWalletMoney);	
						}
						System.out.println("Old Wallet amount:"+oldWalletMoney);
						System.out.println("New Wallet amount:"+newWalletMoney);
						}
										
				
				catch (SQLException e) {
					String msg = "Cannot execute walletquery in putBuyTrade";
					//logger.error(msg + ": " + e);
					throw new DatabaseException(msg, e);
				}
		
		if(!flag) {
			throw new IllegalArgumentException("Client doesn't exist");
		}
		try (PreparedStatement stmt = conn.prepareStatement(sql)) { 
		
		}
	 
	}

@Override
public void putSellTrade() {
	// TODO Auto-generated method stub
	
}
	
	
		
		
}

