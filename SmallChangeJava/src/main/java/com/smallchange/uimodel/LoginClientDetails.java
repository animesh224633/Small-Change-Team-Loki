package com.smallchange.uimodel;

import java.util.Objects;

public class LoginClientDetails {
	
	String clientMail;
	String password;
	public LoginClientDetails() {}
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

	@Override
	public int hashCode() {
		return Objects.hash(clientMail, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginClientDetails other = (LoginClientDetails) obj;
		return Objects.equals(clientMail, other.clientMail) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "LoginClientDetails [clientMail=" + clientMail + ", password=" + password + "]";
	}
	
	

}
