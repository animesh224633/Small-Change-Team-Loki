package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.util.Objects;

public class TradeHistory {
	String name;
	public TradeHistory() {
		
	}
	public TradeHistory(String name, String code, int quantity, String type, BigDecimal price, String assetClass) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
		this.assetClass = assetClass;
	}
	String code;
	int quantity;
	String type;
	BigDecimal price;
	String assetClass;
	
	
	@Generated
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null)
			throw new NullPointerException("Name cannot be null");
		if(name=="")
			throw new IllegalArgumentException("Name cannot be empty");
		this.name = name;
	}
	@Generated
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(code==null)
			throw new NullPointerException("Code cannot be null");
		if(code=="")
			throw new IllegalArgumentException("Code cannot be empty");
		this.code = code;
	}
	@Generated
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		
		if(quantity<0) {
			throw new IllegalArgumentException("Quantity cannot be negative");
		}
		this.quantity = quantity;
	}
	@Generated
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if(type==null)
			throw new NullPointerException("Type cannot be null");
		if(type=="")
			throw new IllegalArgumentException("Type cannot be empty");
		this.type = type;
	}
	@Generated
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		if(price==null) {
			throw new NullPointerException("Price cannot be null");
		}
		if(price.compareTo(BigDecimal.ZERO)<0) {
			throw new IllegalArgumentException("Price cannot be negative");
		}
		this.price = price;
	}
	@Generated
	public String getAssetClass() {
		return assetClass;
	}
	public void setAssetClass(String assetClass) {
		if(assetClass==null)
			throw new NullPointerException("Asset class cannot be null");
		if(assetClass=="")
			throw new IllegalArgumentException("Asset class cannot be empty");
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
