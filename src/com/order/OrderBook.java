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
        while(!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            Order buyOrder = buyOrders.peek();
            Order sellOrder = sellOrders.peek();

            if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                int matchedQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

                System.out.println("Matched " + matchedQuantity + " of " + buyOrder.getSymbol() + " at " + sellOrder.getPrice());

                buyOrder.setQuantity(buyOrder.getQuantity() - matchedQuantity);
                sellOrder.setQuantity(sellOrder.getQuantity() - matchedQuantity);

                // remove the orders if fully filled
                if (buyOrder.getQuantity() == 0) buyOrders.poll();
                if (sellOrder.getQuantity() == 0) sellOrders.poll();
            } else {
                break;
            }
        }
    }
}
