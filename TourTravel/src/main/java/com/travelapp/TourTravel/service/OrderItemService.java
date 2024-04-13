package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem saveOrderItem(OrderItem oi);
    List<OrderItem> getAllByOrderId(int id);
    void deleteOrderItemId(int id);
}
