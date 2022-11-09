package com.smallchange.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smallchange.integration.mapper.SmallChangeWalletMapper;
import com.smallchange.integration.mapper.TradeHistoryMapper;
import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.SmallChangeWalletAmount;
import com.smallchange.uimodel.WalletUpdateDetails;
import com.smallchange.uimodel.WalletUpdateValues;

@Repository
public class SmallChangeWalletMyBatisImpl implements SmallChangeWalletMyBatisDao{
	
	@Autowired
	private Logger logger;

	@Autowired
	private SmallChangeWalletMapper smallChangeWalletMapper;

	@Override
	public SmallChangeWalletAmount getUserSmallChangeWalletAmount(String clientId) {
		
		return smallChangeWalletMapper.getSmallChangeWalletAmount(clientId);
	}

	@Override
	public List<BankAccountDetails> getBankAccountDetails(String clientId) {
		
		return smallChangeWalletMapper.getBankAccountDetails(clientId);
	}

	@Override
	public WalletUpdateDetails updateSmallChangeWallet(WalletUpdateValues walletUpdateValues) {
		String clientId = walletUpdateValues.getClientId();
		System.out.println("****" + clientId);
		BigDecimal walletAmount = getUserSmallChangeWalletAmount(clientId).getClientSmallChangeWallet();
		List<BankAccountDetails> list = new ArrayList<>();
		list = getBankAccountDetails(clientId);
		for (BankAccountDetails bankAccountDetails : list) {
			if(bankAccountDetails.getAccountName().equals(walletUpdateValues.getAccountName())) {
				if(bankAccountDetails.getAccountBalance().compareTo(walletUpdateValues.getRechargeAmount())>=0) {
					System.out.println("ola" + bankAccountDetails.getAccountName());
					BigDecimal updatedWalletAmount = walletAmount.add(walletUpdateValues.getRechargeAmount());
					BigDecimal updatedAccountValue = bankAccountDetails.getAccountBalance().subtract(walletUpdateValues.getRechargeAmount());
					smallChangeWalletMapper.updateClientTable(clientId,updatedWalletAmount);
					//System.out.println(bankAccountDetails.getAccountName());
					smallChangeWalletMapper.updateClientFinanceTable(clientId,bankAccountDetails.getAccountName(),updatedAccountValue);
				}
			}
		}
		WalletUpdateDetails walletUpdateDetails = new WalletUpdateDetails();
		walletUpdateDetails.setMessage("success");
		return walletUpdateDetails;
	}

}
