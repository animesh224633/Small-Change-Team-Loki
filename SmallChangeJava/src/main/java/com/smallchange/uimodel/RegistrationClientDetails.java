package com.smallchange.uimodel;

public class RegistrationClientDetails {
	
	String clientMail;
	String password;
	String clientName;
	public RegistrationClientDetails(String clientMail, String password, String clientName) {
		super();
		this.clientMail = clientMail;
		this.password = password;
		this.clientName = clientName;
	}
	public String getClientMail() {
		return clientMail;
	}
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	

}
