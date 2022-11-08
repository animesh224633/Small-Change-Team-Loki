package com.smallchange.integration.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.Portfolio;
import com.smallchange.uimodel.SmallChangeWalletAmount;

@Mapper
public interface SmallChangeWalletMapper {

	SmallChangeWalletAmount getSmallChangeWalletAmount(@Param("clientId") String clientId);

	List<BankAccountDetails> getBankAccountDetails(@Param("clientId") String clientId);

	void updateClientTable(@Param("clientId") String cientId, @Param("updatedWalletAmount") BigDecimal updatedWalletAmount);

	void updateClientFinanceTable(@Param("clientId") String cientId,@Param("accountName") String accountName,
			@Param("updatedAccountValue") BigDecimal updatedAccountValue);

}
