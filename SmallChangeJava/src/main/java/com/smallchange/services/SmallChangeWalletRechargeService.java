package com.smallchange.services;

import java.math.BigDecimal;
import java.util.List;

import com.smallchange.dao.ClientDAO;
import com.smallchange.dao.ClientFinanceDetailsDAO;
import com.smallchange.model.WalletUpdateDetails;

public class SmallChangeWalletRechargeService {
	
	
	//Function which takes the client id and the account name and recharge amount from frnt end and recharges 
	//the small change wallet balance accordingly
	
	public WalletUpdateDetails updateClientWallet(String clientId, String accountName, BigDecimal rechargeAmount,
			List<ClientDAO> clientTable, List<ClientFinanceDetailsDAO> clientFinanceDetailsTable) {
		
		WalletUpdateDetails walletUpdateDetails = new WalletUpdateDetails();
		
		for(ClientFinanceDetailsDAO clientFinanceTableIterator : clientFinanceDetailsTable) {
			if(clientFinanceTableIterator.getClientId().equals(clientId)) {
				if(clientFinanceTableIterator.getAccountName().equals(accountName)) {
					BigDecimal accountBalance = clientFinanceTableIterator.getAccountBalance();
					BigDecimal remainingAccountBalance = accountBalance.subtract(rechargeAmount);
					if(remainingAccountBalance.signum()== -1) {
						String message ="Cannot add money to wallet. Insuficient funds.";
						walletUpdateDetails.setMessage(message);
						return walletUpdateDetails;
					}
					ClientDAO clientDAO = new ClientDAO();
					clientDAO = clientTable.stream().filter(iterator -> iterator.getClientId().equals(clientId)).findAny().orElse(null);
					BigDecimal clientOldWalletAmount = clientDAO.getClientSmallChangeWallet();
					clientDAO.setClientSmallChangeWallet(clientOldWalletAmount.add(rechargeAmount));
					String message = "Amount added to wallet successfully";
					walletUpdateDetails.setMessage(message);
					//Checking if wallet amount is getting updated or not
					System.out.println("Client's updated wallet amount "+clientDAO.getClientSmallChangeWallet());
					return walletUpdateDetails;
				}
				else
					throw new IllegalArgumentException("Account not found");
			}		
		}
		throw new IllegalArgumentException("ClientID not found");
	}
	
	
}
