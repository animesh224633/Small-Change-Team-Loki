package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public  class Order {
	
	String code;
	int quantity;

	String clientId;
	String orderId;
	LocalDate timestamp;
	String direction;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clientId, code, direction, orderId, quantity, timestamp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(code, other.code)
				&& Objects.equals(direction, other.direction) && Objects.equals(orderId, other.orderId)
				&& quantity == other.quantity && Objects.equals(timestamp, other.timestamp);
	}
	@Override
	public String toString() {
		return "Order [code=" + code + ", quantity=" + quantity + ", clientId=" + clientId + ", orderId=" + orderId
				+ ", timestamp=" + timestamp + ", direction=" + direction + ", getCode()=" + getCode()
				+ ", getQuantity()=" + getQuantity() + ", getClientId()=" + getClientId() + ", getOrderId()="
				+ getOrderId() + ", getTimestamp()=" + getTimestamp() + ", getDirection()=" + getDirection()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
}
