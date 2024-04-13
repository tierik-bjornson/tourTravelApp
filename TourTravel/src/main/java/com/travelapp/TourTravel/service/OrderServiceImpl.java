package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.Orders;

import com.travelapp.TourTravel.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    public Orders saveOrder(Orders o){
        return orderRepository.save(o);

    }

    public List<Orders> GetAllOrder(){
        return orderRepository.findAll();
    }

    public Orders findOrderById(int id){
        return orderRepository.findById(id);
    }

    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public void deleteOrderById(int id){
        orderRepository.deleteById(id);
    }

    public List<Orders> findAllOrderByPaymentMethod(String paymentMethod){
        return orderRepository.findAllByPayment_Method(paymentMethod);
    }

    public List<Orders> getAllOrderByCusId(String CusId){
        return orderRepository.findAllByCusId(CusId);
    }

    public List<Orders> findAllByPayment_Method(String payment_Method, String user_id) {
        return orderRepository.findAllByPayment_Methods(payment_Method, user_id);
    }





}
