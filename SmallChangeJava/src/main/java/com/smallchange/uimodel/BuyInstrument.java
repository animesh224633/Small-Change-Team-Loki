package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.util.Objects;

public class BuyInstrument {

	private String code;
	private String name;
    private String category;
	private BigDecimal currentPrice;
	
	public BuyInstrument(String code2, String name2, String category2, BigDecimal current) {
	    this.code=code2;
		this.name=name2;
		this.category=category2;
		this.currentPrice=current;
		
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
		return Objects.hash(category, code, currentPrice, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyInstrument other = (BuyInstrument) obj;
		return Objects.equals(category, other.category) && Objects.equals(code, other.code)
				&& Objects.equals(currentPrice, other.currentPrice) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "BuyInstrument [code=" + code + ", name=" + name + ", category=" + category + ", currentPrice="
				+ currentPrice + "]";
	}
	
	
}

