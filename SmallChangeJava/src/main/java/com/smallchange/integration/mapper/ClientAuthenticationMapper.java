package com.smallchange.integration.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smallchange.uimodel.ClientSendBackDetails;
@Mapper
public interface ClientAuthenticationMapper {

	ClientSendBackDetails getLoginAuthenticationDetails(@Param("clientMail") String clientMail,
			@Param("password") String password );

}
