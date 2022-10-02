package com.smallchange.model;

import java.math.BigDecimal;
import java.util.Objects;

public class SellInstrument {
	private String code;

	private String name;
	private BigDecimal currentPrice;
	private String category;
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
		SellInstrument other = (SellInstrument) obj;
		return Objects.equals(category, other.category) && Objects.equals(code, other.code)
				&& Objects.equals(currentPrice, other.currentPrice) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "SellInstrument [code=" + code + ", name=" + name + ", currentPrice=" + currentPrice + ", category="
				+ category + "]";
	}

}
