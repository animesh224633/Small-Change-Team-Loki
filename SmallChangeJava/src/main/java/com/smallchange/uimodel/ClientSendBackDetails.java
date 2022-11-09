package com.smallchange.uimodel;

import java.util.Objects;

public class ClientSendBackDetails {
	
	String clientId;
	public ClientSendBackDetails(String clientId, String message) {
		super();
		this.clientId = clientId;
		this.message = message;
	}
	public ClientSendBackDetails() {}
	String message;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clientId, message);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientSendBackDetails other = (ClientSendBackDetails) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(message, other.message);
	}
	@Override
	public String toString() {
		return "ClientSendBackDetails [clientId=" + clientId + ", message=" + message + "]";
	}
	
	

}
