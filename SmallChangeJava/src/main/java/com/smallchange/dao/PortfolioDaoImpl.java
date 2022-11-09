/**
 * 
 */
package com.smallchange.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.smallchange.uimodel.Portfolio;


/**
 * @author a683691
 *
 */
public class PortfolioDaoImpl implements PortfolioDao {
	private DataSource datasource;

	public PortfolioDaoImpl(DataSource datasource) {
		this.datasource = datasource;
	}


	@Override
	public List<Portfolio> getUserPortfolio(int clientId) throws DatabaseException {
		// TODO Auto-generated method stub
		String sql="""
				SELECT I.NAME, I.CODE, I.CURRENT_PRICE, I.CATEGORY,H.BUYPRICE,H.QUANTITY
				FROM holdings H
				JOIN instrument I
				ON h.code=i.code
				WHERE H.CLIENT_ID=?
				""";
		if (clientId <=0) {
			throw new DatabaseException("Client Id cannot be less than or equal to zero " + clientId);
		}
		try (Connection conn = datasource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)){
			System.out.println("Connection established");
			stmt.setInt(1, clientId);
			ResultSet rs=stmt.executeQuery();
			System.out.println("Query executed");
			List<Portfolio> clientportfolio = new ArrayList<Portfolio>();
			
			int count=0;
			while(rs.next()) {
				clientportfolio.add(getAndHandleResults(rs));
				count++;
				
			}
			System.out.println(count);
//			clientportfolio.setPortfolioMutualFundList(mfList);
//			clientportfolio.setPortfolioStockList(stockList);
			return clientportfolio;
			
		}catch(SQLException e){
			throw new DatabaseException("Database Access Error");
			
		}
		
	}
	private Portfolio getAndHandleResults(ResultSet rs) throws SQLException {
		Portfolio portfolioIterator =new Portfolio();
		
		// TODO Auto-generated method stub
		
		String code=rs.getString("CODE");
		int quantity=rs.getInt("QUANTITY");
		String name=rs.getString("NAME");
		BigDecimal buyPrice=BigDecimal.valueOf(rs.getFloat("BUYPRICE")).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal currentPrice=BigDecimal.valueOf(rs.getFloat("CURRENT_PRICE")).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal investedAmount=buyPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal currentValue=currentPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal profitLoss=currentValue.subtract(investedAmount).setScale(2, RoundingMode.HALF_DOWN);
		BigDecimal percentChange=profitLoss.divide(investedAmount, 2, RoundingMode.HALF_DOWN).multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_DOWN);
//		System.out.println(code + "percentcahnge" + percentChange);
		//based on category we add mutualfund or stock to our portfolio list
		
		System.out.println(name+ "code " + code + "quantity "+quantity + "buyp "+buyPrice+"currp "+currentPrice+"investamt "+investedAmount + "currv "+currentValue+ "profitloss "+profitLoss+ "percentChange "+percentChange);
				
		
		
		
		return portfolioIterator;
	}



}
