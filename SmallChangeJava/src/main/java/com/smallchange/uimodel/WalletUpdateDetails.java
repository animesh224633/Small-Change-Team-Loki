package com.smallchange.uimodel;

import java.util.Objects;

public class WalletUpdateDetails {
	
	String message;

	public WalletUpdateDetails() {}
	public WalletUpdateDetails(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletUpdateDetails other = (WalletUpdateDetails) obj;
		return Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "WalletUpdateDetails [message=" + message + "]";
	}
	
	

}
