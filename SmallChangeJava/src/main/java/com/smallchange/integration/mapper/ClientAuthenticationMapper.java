package com.smallchange.integration.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smallchange.uimodel.ClientSendBackDetails;
@Mapper
public interface ClientAuthenticationMapper {

	ClientSendBackDetails getLoginAuthenticationDetails(@Param("clientMail") String clientMail,
			@Param("password") String password );
	
	void putRegistrationAuthenticationDetails(@Param("clientId") String clientId,
			@Param("clientName") String clientName, @Param("clientMail") String clientMail,
			@Param("password") String password, @Param("clientWallet") BigDecimal clientWallet  );

}
