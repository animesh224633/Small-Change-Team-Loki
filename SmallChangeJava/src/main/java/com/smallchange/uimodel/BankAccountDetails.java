package com.smallchange.uimodel;

import java.math.BigDecimal;

public class BankAccountDetails {
	
	String accountName;
	BigDecimal accountBalance;
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
}
