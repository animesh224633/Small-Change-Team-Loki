package com.smallchange.model;

import java.math.BigDecimal;

public class Client {
	String clientId;
	String clientName;
	String clientMail;
	BigDecimal clientSmallChangeWallet;
	String password;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientMail() {
		return clientMail;
	}
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	public BigDecimal getClientSmallChangeWallet() {
		return clientSmallChangeWallet;
	}
	public void setClientSmallChangeWallet(BigDecimal clientSmallChangeWallet) {
		this.clientSmallChangeWallet = clientSmallChangeWallet;
	}
	
	
}
