package com.smallchange.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.smallchange.model.Instrument;
import com.smallchange.uimodel.TradeHistory;

//sample trade


public class TradeHistoryImpl implements TradeHistoryDao {
	
	
	private DataSource datasource;

	public TradeHistoryImpl(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public List<TradeHistory> getTradeHistory(String clientId) {
		String sql="""
				select o.client_id,o.code,o.quantity,o.Buy_price,o.direction,i.name,i.category from orders o join instrument i on o.code=i.code where client_id=?
				""";
		try (Connection conn = datasource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)){
		
			stmt.setString(1, clientId);
			ResultSet rs=stmt.executeQuery();
			List<TradeHistory> tradeHistory=new ArrayList<>();
	
			while(rs.next()) {
				tradeHistory.add(getResult(rs));
			}
			return tradeHistory;
			
		}catch(SQLException e){
			throw new IllegalArgumentException("Database Access Error");
			
		}
	}
	
	public TradeHistory getResult(ResultSet rs) {
		try {
			String code=rs.getString("CODE");
			int quantity=rs.getInt("QUANTITY");
			BigDecimal price=BigDecimal.valueOf(rs.getFloat("BUY_PRICE"));
			String type=rs.getString("DIRECTION");
			String name=rs.getString("NAME");
			String asset=rs.getString("CATEGORY");
			TradeHistory tradeHistory=new TradeHistory(name,code,quantity,type,price,asset);
			return tradeHistory;
		}catch(SQLException e) {
			throw new DatabaseException("new error");
		}
	}
}


