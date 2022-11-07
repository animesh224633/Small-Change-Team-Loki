package com.smallchange.integration;

import com.smallchange.uimodel.ClientSendBackDetails;
import com.smallchange.uimodel.LoginClientDetails;
import com.smallchange.uimodel.RegistrationClientDetails;

public interface ClientAuthenticationMyBatisDao {
	
	public ClientSendBackDetails loginAuthenticationService(LoginClientDetails loginClientDetails);
	public ClientSendBackDetails registrationAuthenticationService(RegistrationClientDetails registrationClientDetails);

}
