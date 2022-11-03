package com.smallchange.integration;

import org.apache.ibatis.annotations.Param;

import com.smallchange.uimodel.ClientSendBackDetails;

public interface ClientAuthenticationMapper {

	ClientSendBackDetails getLoginAuthenticationDetails(@Param("clientMail") String clientMail,
			@Param("password") String password );

}
