package com.order;

public class Order {
    private final String orderId;
    private final String symbol;
    private final OrderType type;
    private final double price;
    private int quantity;
    private final long timestamp;  // to ensure FIFO for same-price orders

    public Order(String orderId, String symbol, OrderType type, double price, int quantity) {
        this.orderId = orderId;
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = System.currentTimeMillis();
    }

    // getters and setters
    public String getOrderId() {
        return orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public OrderType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
