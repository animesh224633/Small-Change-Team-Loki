package com.smallchange.uimodel;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;



public class BuyOrder extends Order{
    
    String code;
    int quantity;
    BigDecimal buyPrice;
    String clientId;
    String orderId;
    LocalDate timestamp;
    String direction;
    String holding_id;
    public String getHolding_id() {
        return holding_id;
    }
    public void setHolding_id(String holding_id) {
        this.holding_id = holding_id;
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
    public String toString() {
        return "BuyOrder [code=" + code + ", quantity=" + quantity + ", buyPrice=" + buyPrice + ", clientId=" + clientId
                + ", orderId=" + orderId + ", timestamp=" + timestamp + ", direction=" + direction + ", holding_id="
                + holding_id + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + Objects.hash(buyPrice, clientId, code, direction, holding_id, orderId, quantity, timestamp);
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
        BuyOrder other = (BuyOrder) obj;
        return Objects.equals(buyPrice, other.buyPrice) && Objects.equals(clientId, other.clientId)
                && Objects.equals(code, other.code) && Objects.equals(direction, other.direction)
                && Objects.equals(holding_id, other.holding_id) && Objects.equals(orderId, other.orderId)
                && quantity == other.quantity && Objects.equals(timestamp, other.timestamp);
    }
    
}