package com.smallchange.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import com.smallchange.model.Client;
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
			stmt.setString(1, clientMail);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
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
		ClientSendBackDetails clientSendBackDetails = new ClientSendBackDetails();
		Client clientDAO = new Client();
		
		if( clientName == null || clientName.trim().length()==0) {
			String message = "Name cannot be empty or null";
			clientSendBackDetails.setMessage(message);
			return clientSendBackDetails;
		}
		if(clientMail == null || clientMail.trim().length()==0) {
			String message = "Mail cannot be empty or null";
			clientSendBackDetails.setMessage(message);
			return clientSendBackDetails;
		}
		if(password == null || password.trim().length()==0) {
			String message = "Password cannot be empty or null";
			clientSendBackDetails.setMessage(message);
			return clientSendBackDetails;
		}
		String sql="""
				select * from client where client_name=?
				""";
		try (Connection conn = datasource.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, clientName);
			ResultSet rs=stmt.executeQuery();
			System.out.println("hhh");
			int count=0;
			while(rs.next()) {
				count++;
			}
			System.out.println(count);
			String message;
			if(count!=0) {
				message = "client mail already exists.";
				clientSendBackDetails.setMessage(message);
				return clientSendBackDetails;
			}
			else {
				String sql1="""
						INSERT INTO CLIENT (CLIENT_ID, CLIENT_NAME, CLIENT_MAIL,CLIENT_SMALLCHANGE_WALLET,PASSWORD)  VALUES(?,?,?,?,?)
							""";
				
				PreparedStatement stmt1 = conn.prepareStatement(sql1);
				stmt1.setString(1, UUID.randomUUID().toString());
				stmt1.setString(2, clientName);
				stmt1.setString(3, clientMail);
				stmt1.setFloat(4,0.0f);
				stmt1.setString(5, password);
				stmt1.executeUpdate();
				
				message = "registered successfully";
			}
			clientSendBackDetails.setMessage(message);
			return clientSendBackDetails;
			
		}catch(SQLException e){
			throw new IllegalArgumentException("Database Access Error");
			
		}
	
	}

}
