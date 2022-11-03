package com.smallchange.integration;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.Portfolio;

@Repository("ClientAauthentictionDAO")
public class ClientAauthentictionMyBatisImpl {
	
	@Autowired
	private Logger logger;

	@Autowired
	private ClientAuthenticationMapper clientAuthenticationMapper;
	
	public ClientSendBackDetails loginAuthenticationService(String clientMail, String password)  {
		
		System.out.println("lala" + clientAuthenticationMapper.getLoginAuthenticationDetails(clientMail, password).getClientId() );
		
		return clientAuthenticationMapper.getLoginAuthenticationDetails(clientMail, password);
	}

}
