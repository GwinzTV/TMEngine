package com.order;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook {
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBook() {
        buyOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice).reversed().thenComparingLong(Order::getTimestamp));
        sellOrders = new PriorityQueue<>(Comparator.comparingDouble(Order::getPrice).thenComparingLong(Order::getTimestamp));
    }

    public void addOrder(Order order) {
        if (order.getType() == OrderType.BUY) {
            buyOrders.offer(order);
        } else if (order.getType() == OrderType.SELL) {
            sellOrders.offer(order);
        }
        matchOrders();
    }

    private void matchOrders() {
        //
    }
}
