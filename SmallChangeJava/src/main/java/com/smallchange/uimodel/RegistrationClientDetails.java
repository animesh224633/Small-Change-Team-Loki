package com.smallchange.uimodel;

import java.util.Objects;

public class RegistrationClientDetails {
	
	String clientMail;
	String password;
	String clientName;
	public RegistrationClientDetails() {}
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
	@Override
	public int hashCode() {
		return Objects.hash(clientMail, clientName, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationClientDetails other = (RegistrationClientDetails) obj;
		return Objects.equals(clientMail, other.clientMail) && Objects.equals(clientName, other.clientName)
				&& Objects.equals(password, other.password);
	}
	@Override
	public String toString() {
		return "RegistrationClientDetails [clientMail=" + clientMail + ", password=" + password + ", clientName="
				+ clientName + "]";
	}
	
	

}
