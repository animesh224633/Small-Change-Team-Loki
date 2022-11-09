package com.smallchange.integration;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.integration.mapper.ClientAuthenticationMapper;
import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.LoginClientDetails;
import com.smallchange.uimodel.RegistrationClientDetails;

@Repository
public class ClientAauthentictionMyBatisImpl implements ClientAuthenticationMyBatisDao{
	
	@Autowired
	private Logger logger;

	@Autowired
	private ClientAuthenticationMapper clientAuthenticationMapper;
	
	@Override
	public ClientSendBackDetails loginAuthenticationService(LoginClientDetails loginClientDetails)  {
		
		//System.out.println("lala" + clientAuthenticationMapper.getLoginAuthenticationDetails(clientMail, password).getClientId() );
		
		return clientAuthenticationMapper.getLoginAuthenticationDetails(loginClientDetails.getClientMail(), loginClientDetails.getPassword());
	}
	
	@Override
	public ClientSendBackDetails registrationAuthenticationService(RegistrationClientDetails registrationClientDetails) {
		// TODO Auto-generated method stub
		 clientAuthenticationMapper.putRegistrationAuthenticationDetails(UUID.randomUUID().toString(), 
				registrationClientDetails.getClientName(), registrationClientDetails.getClientMail(),
				registrationClientDetails.getPassword(), BigDecimal.valueOf(2000));
		 ClientSendBackDetails clientSendBackDetails = new ClientSendBackDetails();
		 clientSendBackDetails.setMessage("Success");;
		 return clientSendBackDetails;
	}

	@Override
	public ClientSendBackDetails checkClientMailList(String clientMail) {
		
		return clientAuthenticationMapper.getClientMail(clientMail);
	}

}
