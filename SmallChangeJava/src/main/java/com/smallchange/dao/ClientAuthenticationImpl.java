package com.smallchange.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.smallchange.uimodel.ClientSendBackDetails;

public class ClientAuthenticationImpl implements ClientAuthenticationDao{

	private DataSource datasource;

	public ClientAuthenticationImpl(DataSource datasource) {
		this.datasource = datasource;
	}

	@Override
	public ClientSendBackDetails loginAuthenticationService(String clientMail, String password) {
		ClientSendBackDetails clientSendBackDetails = new ClientSendBackDetails();
		String sql="""
				select * from client where client_name=? and password=?
				""";
		try (Connection conn = datasource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)){
			System.out.println("hi");
			stmt.setString(1, clientMail);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			System.out.println("hhh");
			int count=0;
			while(rs.next()) {
				count++;
			}
			String message;
			if(count!=0) {
				message = "authentication successful";
			}
			else {
				message = "client mail or password incorrect";
			}
			clientSendBackDetails.setMessage(message);
			return clientSendBackDetails;
			
		}catch(SQLException e){
			throw new IllegalArgumentException("Database Access Error");
			
		}
	}

	@Override
	public ClientSendBackDetails registrationAuthenticationService(String clientName, String clientMail,
			String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
