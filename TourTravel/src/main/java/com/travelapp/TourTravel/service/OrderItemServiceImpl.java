package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.OrderItem;
import com.travelapp.TourTravel.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem saveOrderItem(OrderItem oi){
        return orderItemRepository.save(oi);
    }

    public List<OrderItem> getAllByOrderId(int id){
        return orderItemRepository.findAllByOrderId(id);
    }

    public void deleteOrderItemId(int id){
        orderItemRepository.deleteById(id);
    }


}
