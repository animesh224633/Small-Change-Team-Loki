package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.util.Objects;

public class SmallChangeWalletAmount {
	
	BigDecimal clientSmallChangeWallet;

	public SmallChangeWalletAmount() {}
	
	public SmallChangeWalletAmount(BigDecimal clientSmallChangeWallet) {
		super();
		this.clientSmallChangeWallet = clientSmallChangeWallet;
	}

	public BigDecimal getClientSmallChangeWallet() {
		return clientSmallChangeWallet;
	}

	public void setClientSmallChangeWallet(BigDecimal clientSmallChangeWallet) {
		this.clientSmallChangeWallet = clientSmallChangeWallet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientSmallChangeWallet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SmallChangeWalletAmount other = (SmallChangeWalletAmount) obj;
		return Objects.equals(clientSmallChangeWallet, other.clientSmallChangeWallet);
	}

	@Override
	public String toString() {
		return "SmallChangeWalletAmount [clientSmallChangeWallet=" + clientSmallChangeWallet + "]";
	}
	
	

}
