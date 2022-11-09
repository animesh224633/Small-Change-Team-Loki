package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccountDetails {
	
	String accountName;
	public BankAccountDetails() {}
	public BankAccountDetails(String accountName, BigDecimal accountBalance) {
		super();
		this.accountName = accountName;
		this.accountBalance = accountBalance;
	}
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
	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, accountName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccountDetails other = (BankAccountDetails) obj;
		return Objects.equals(accountBalance, other.accountBalance) && Objects.equals(accountName, other.accountName);
	}
	@Override
	public String toString() {
		return "BankAccountDetails [accountName=" + accountName + ", accountBalance=" + accountBalance + "]";
	}
	
	
}
