package com.smallchange.uimodel;

public class LoginClientDetails {
	
	String clientMail;
	String password;
	
	public LoginClientDetails(String clientMail, String password) {
		super();
		this.clientMail = clientMail;
		this.password = password;
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
	
	

}
