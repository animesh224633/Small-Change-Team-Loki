package com.smallchange.integration;

import java.util.List;

import com.smallchange.uimodel.BankAccountDetails;
import com.smallchange.uimodel.SmallChangeWalletAmount;
import com.smallchange.uimodel.WalletUpdateDetails;
import com.smallchange.uimodel.WalletUpdateValues;

public interface SmallChangeWalletMyBatisDao {
	
	public SmallChangeWalletAmount getUserSmallChangeWalletAmount(String clientId);
	
	public List<BankAccountDetails> getBankAccountDetails(String clientId);
	
	public WalletUpdateDetails updateSmallChangeWallet(WalletUpdateValues walletUpdateValues);

}
