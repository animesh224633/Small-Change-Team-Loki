package com.smallchange.uimodel;

import java.math.BigDecimal;
import java.util.Objects;

public class Portfolio {
	
	String name;
	public Portfolio(String name, String code, int quantity, BigDecimal buyPrice, BigDecimal currentPrice,
			BigDecimal investedAmount, BigDecimal currentValue, BigDecimal profitOrLoss, BigDecimal percentageChange,
			String category) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.buyPrice = buyPrice;
		this.currentPrice = currentPrice;
		this.investedAmount = investedAmount;
		this.currentValue = currentValue;
		this.profitOrLoss = profitOrLoss;
		this.percentageChange = percentageChange;
		this.category = category;
	}
	String code;
	int quantity;
	BigDecimal buyPrice;
	BigDecimal currentPrice;
	BigDecimal investedAmount;
	BigDecimal currentValue;
	BigDecimal profitOrLoss;
	BigDecimal percentageChange;
	String category;
	
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Portfolio() {
		
	}
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
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public BigDecimal getInvestedAmount() {
		return investedAmount;
	}
	public void setInvestedAmount(BigDecimal investedAmount) {
		this.investedAmount = investedAmount;
	}
	public BigDecimal getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(BigDecimal currentValue) {
		this.currentValue = currentValue;
	}
	public BigDecimal getProfitOrLoss() {
		return profitOrLoss;
	}
	public void setProfiOrLoss(BigDecimal profitOrLoss) {
		this.profitOrLoss = profitOrLoss;
	}
	public BigDecimal getPercentageChange() {
		return percentageChange;
	}
	public void setPercentageChange(BigDecimal percentageChange) {
		this.percentageChange = percentageChange;
	}
	@Override
	public int hashCode() {
		return Objects.hash(buyPrice, category, code, currentPrice, currentValue, investedAmount, name,
				percentageChange, profitOrLoss, quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portfolio other = (Portfolio) obj;
		return Objects.equals(buyPrice, other.buyPrice) && Objects.equals(category, other.category)
				&& Objects.equals(code, other.code) && Objects.equals(currentPrice, other.currentPrice)
				&& Objects.equals(currentValue, other.currentValue)
				&& Objects.equals(investedAmount, other.investedAmount) && Objects.equals(name, other.name)
				&& Objects.equals(percentageChange, other.percentageChange)
				&& Objects.equals(profitOrLoss, other.profitOrLoss) && quantity == other.quantity;
	}
	@Override
	public String toString() {
		return "Portfolio [name=" + name + ", code=" + code + ", quantity=" + quantity + ", buyPrice=" + buyPrice
				+ ", currentPrice=" + currentPrice + ", investedAmount=" + investedAmount + ", currentValue="
				+ currentValue + ", profitOrLoss=" + profitOrLoss + ", percentageChange=" + percentageChange
				+ ", category=" + category + "]";
	}
	
	

}
