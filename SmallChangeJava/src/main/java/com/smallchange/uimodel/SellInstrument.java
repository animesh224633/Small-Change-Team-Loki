package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class SellInstrument {
	
	private String holdingId;
	private String clientId;
	private String code;
	private String name;
	private String category;
	private int quantity;
	private BigDecimal currentPrice ;

	
	
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
	
	
	
	public SellInstrument(String holdingId, String clientId, String code2,String name, String category, int quantity, BigDecimal current) {
	 this.holdingId=holdingId;
	 this.clientId=clientId;
	 this.code=code2;
	 this.quantity=quantity;
	 this.currentPrice=current;
	 this.name=name;
	 this.category=category;
	 
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public int hashCode() {
		return Objects.hash(category, clientId, code, currentPrice, holdingId, name, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellInstrument other = (SellInstrument) obj;
		return Objects.equals(category, other.category) && Objects.equals(clientId, other.clientId)
				&& Objects.equals(code, other.code) && Objects.equals(currentPrice, other.currentPrice)
				&& Objects.equals(holdingId, other.holdingId) && Objects.equals(name, other.name)
				&& quantity == other.quantity;
	}
	@Override
	public String toString() {
		return "SellInstrument [holdingId=" + holdingId + ", clientId=" + clientId + ", code=" + code + ", name=" + name
				+ ", category=" + category + ", quantity=" + quantity + ", currentPrice=" + currentPrice + "]";
	}
	


}
