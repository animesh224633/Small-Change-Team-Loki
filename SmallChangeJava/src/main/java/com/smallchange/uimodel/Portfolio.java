package com.smallchange.uimodel;

import java.math.BigDecimal;

public class Portfolio {
	
	String name;
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
	public Portfolio(String name, String code, int quantity, BigDecimal buyPrice, BigDecimal currentPrice,
			BigDecimal investedAmount, BigDecimal currentValue, BigDecimal profitOrLoss, BigDecimal percentageChange) {
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
	
	

}
