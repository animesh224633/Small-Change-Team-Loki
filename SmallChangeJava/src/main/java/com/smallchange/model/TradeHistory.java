package com.smallchange.model;

import java.math.BigDecimal;
import java.util.Objects;

public class TradeHistory {
	String name;
	String code;
	int quantity;
	String type;
	BigDecimal price;
	String assetClass;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}
	@Override
	public String toString() {
		return "TradeHistory [name=" + name + ", code=" + code + ", quantity=" + quantity + ", type=" + type
				+ ", price=" + price + ", assetClass=" + assetClass + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(assetClass, code, name, price, quantity, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeHistory other = (TradeHistory) obj;
		return Objects.equals(assetClass, other.assetClass) && Objects.equals(code, other.code)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price) && quantity == other.quantity
				&& Objects.equals(type, other.type);
	}
	

}
