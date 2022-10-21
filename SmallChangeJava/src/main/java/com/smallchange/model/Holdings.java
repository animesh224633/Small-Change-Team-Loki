package com.smallchange.model;

import java.math.BigDecimal;

public class Holdings {
	
	String holdingId;
	String clientId;
	String code;
	int quantity;
	BigDecimal buyPrice;
	String name;
	String category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	public String getHoldingId() {
		return holdingId;
	}
	public void setHoldingId(String holdingId) {
		this.holdingId = holdingId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
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
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	

}
