package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class SellOrder extends Order{
	String code;
	int quantity;
	BigDecimal sellPrice;
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
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	@Override
	public String toString() {
		return "SellOrder [code=" + code + ", quantity=" + quantity + ", sellPrice=" + sellPrice + ", clientId="
				+ clientId + ", orderId=" + orderId + ", timestamp=" + timestamp + ", direction=" + direction + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(clientId, code, direction, orderId, quantity, sellPrice, timestamp);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellOrder other = (SellOrder) obj;
		return Objects.equals(clientId, other.clientId) && Objects.equals(code, other.code)
				&& Objects.equals(direction, other.direction) && Objects.equals(orderId, other.orderId)
				&& quantity == other.quantity && Objects.equals(sellPrice, other.sellPrice)
				&& Objects.equals(timestamp, other.timestamp);
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
}
