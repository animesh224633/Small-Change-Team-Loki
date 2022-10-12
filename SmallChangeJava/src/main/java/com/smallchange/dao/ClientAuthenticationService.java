package com.smallchange.dao;

import com.smallchange.model.Client;
import com.smallchange.uimodel.ClientSendBackDetails;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ClientAuthenticationService {
	
	//Function for logging the client in
	public ClientSendBackDetails loginAuthenticationService(String clientMail,String password,
			List<Client> clientTable) {
		
		ClientSendBackDetails clientSendBackDetails = new ClientSendBackDetails();
		
		for(Client clientTableIterator : clientTable) {
			if(clientTableIterator.getClientMail().equals(clientMail) && clientTableIterator.getPassword().equals(password)) {
				String message = "authentication successful";
				clientSendBackDetails.setClientId(clientTableIterator.getClientId());
				clientSendBackDetails.setMessage(message);
				return clientSendBackDetails;
			}
		}
		String message = "client mail or password incorrect";
		clientSendBackDetails.setMessage(message);
		return clientSendBackDetails;
	}
	
	//Function for registering a new client
	public ClientSendBackDetails registrationAuthenticationService(String clientName, 
			String clientMail, String password, List<Client> clientTable) {
		
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
		
		for(Client clientTableIterator : clientTable) {
			if(clientTableIterator.getClientMail().equals(clientMail)) {
				String message = "client mail already exists.";
				clientSendBackDetails.setMessage(message);
				return clientSendBackDetails;
			}
		}
		clientDAO.setClientId(UUID.randomUUID().toString());
		clientDAO.setClientMail(clientMail);
		clientDAO.setClientName(clientName);
		clientDAO.setClientSmallChangeWallet(BigDecimal.ZERO);
		clientDAO.setPassword(password);
		clientTable.add(clientDAO);
		
		System.out.println("Checking if client is successfully added with all the details ");
		System.out.println(clientDAO.getClientId());
		System.out.println(clientDAO.getClientMail());
		System.out.println(clientDAO.getClientName());
		System.out.println(clientDAO.getClientSmallChangeWallet());
		System.out.println(clientDAO.getPassword());
		
		String message = "User added successfully";
		clientSendBackDetails.setMessage(message);
		return clientSendBackDetails;
	}
}
