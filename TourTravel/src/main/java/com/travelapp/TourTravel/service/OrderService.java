package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.Orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface OrderService {
    List<Orders> GetAllOrder();
    Orders saveOrder(Orders o);
    Orders findOrderById(int id);
    Page<Orders> findAll(Pageable pageable);
    void deleteOrderById(int id);
    List<Orders> findAllOrderByPaymentMethod(String paymentMethod);
    List<Orders> getAllOrderByCusId(String CusId);
    List<Orders> findAllByPayment_Method(String payment_Method, String user_id);
}
