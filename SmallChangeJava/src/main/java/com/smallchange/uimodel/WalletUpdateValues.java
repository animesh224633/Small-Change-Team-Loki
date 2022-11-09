package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletUpdateValues {
	
	String clientId;
	String accountName;
	BigDecimal rechargeAmount;
	public WalletUpdateValues(String clientId, String accountName, BigDecimal rechargeAmount) {
		super();
		this.clientId = clientId;
		this.accountName = accountName;
		this.rechargeAmount = rechargeAmount;
	}
	
	public WalletUpdateValues() {}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(accountName, clientId, rechargeAmount);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletUpdateValues other = (WalletUpdateValues) obj;
		return Objects.equals(accountName, other.accountName) && Objects.equals(clientId, other.clientId)
				&& Objects.equals(rechargeAmount, other.rechargeAmount);
	}
	@Override
	public String toString() {
		return "WalletUpdateValues [clientId=" + clientId + ", accountName=" + accountName + ", rechargeAmount="
				+ rechargeAmount + "]";
	}
	
	

}
