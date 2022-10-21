package com.smallchange.dao;

import com.smallchange.uimodel.ClientSendBackDetails;

public interface ClientAuthenticationDao {
	public ClientSendBackDetails loginAuthenticationService(String clientMail,String password);
	public ClientSendBackDetails registrationAuthenticationService(String clientName, 
			String clientMail, String password);

}
