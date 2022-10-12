package com.smallchange.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//sample trade


public class TradeHistoryImpl implements TradeHistory {
	
	
	private DataSource datasource;

	public TradeHistoryImpl(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public List<TradeHistory> getTradeHistory(String clientId) {
		String sql="""
				select client_id from client where client_id='1'
				""";
		try (Connection conn = datasource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)){
			System.out.println("hi");
			ResultSet rs=stmt.executeQuery();
			System.out.println("hhh");
			List<TradeHistory> client=new ArrayList<>();
			int count=0;
			while(rs.next()) {
				count++;
			}
			System.out.println(count);
			return null;
			
		}catch(SQLException e){
			throw new IllegalArgumentException("Database Access Error");
			
		}
	}
}


