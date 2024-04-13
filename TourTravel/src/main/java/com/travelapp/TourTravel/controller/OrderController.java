package com.travelapp.TourTravel.controller;
import com.travelapp.TourTravel.dto.OrderDTO;

import com.travelapp.TourTravel.entity.*;
import org.modelmapper.ModelMapper;
import com.travelapp.TourTravel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    TourService tourService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CartService cartService;
    @Autowired
    OrderItemService orderItemService;

    private ModelMapper modelMapper;

    @GetMapping(path = "/{user_id}")
    public ResponseEntity<List<OrderDTO>> getOrder( String user_id) {
        System.out.println(user_id);
        List<Orders> listOrder = orderService.getAllOrderByCusId(user_id);
        List<OrderDTO> listOrderDto = new ArrayList<>();
        for(Orders o: listOrder) {
            OrderDTO orderDto = modelMapper.map(o, OrderDTO.class);
            System.out.println(orderDto.getId());
            listOrderDto.add(orderDto);
        }
        return new ResponseEntity<>(listOrderDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrder() {
        List<Orders> orders =orderService.GetAllOrder();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }







//    @PostMapping(path = "/placeorder", consumes = "application/x-www-form-urlencoded")
//    public ResponseEntity<Orders> placeOrder(String user_id, String fullname, String phoneNumber, String address, String paymentMethod){
//        List<Cart> listCart = cartService.getAllCartbyCusId(user_id);
//        Orders newOrder = new Orders();
//        Customer user = customerService.findByIdandRole(user_id, "customer");
//        long millis = System.currentTimeMillis();
//        Date booking_date = new java.sql.Date(millis);
//        int total=0;
//        for(Cart y: listCart) {
//            total += y.getTour().getPrice() * y.getCount();
//        }
//        newOrder.setCustomer(user);
//        newOrder.setFullname(fullname);
//        newOrder.setBooking_date(booking_date);
//
//        newOrder.setEmail(user.getEmail());
//        newOrder.setPayment_method(paymentMethod);
//        newOrder.setAddress(address);
//        newOrder.setNote(null);
//        newOrder.setPhone(phoneNumber);
//        newOrder.setStatus("Pending");
//        newOrder.setTotal(total);
//
//        newOrder = orderService.saveOrder(newOrder);
//
//        for(Cart y:listCart) {
//            if(y.getCount()>y.getTour().getQuantity()) {
//                orderService.deleteOrderById(newOrder.getId());
//                return new ResponseEntity<>(null, HttpStatus.OK);
//            }
//            y.getTour().setQuantity(y.getTour().getQuantity()-y.getCount());
//            y.getTour().setSold(y.getTour().getSold()+y.getCount());
//            tourService.saveTour(y.getTour());
//            OrderItem newOrder_Item = new OrderItem();
//            newOrder_Item.setCount(y.getCount());
//            newOrder_Item.setOrder(newOrder);
//            newOrder_Item.setTour(y.getTour());
//            newOrder_Item = orderItemService.saveOrderItem(newOrder_Item);
//            cartService.deleteById(y.getId());
//        }
//        newOrder = orderService.findOrderById(newOrder.getId());
//        return new ResponseEntity<>(newOrder, HttpStatus.OK);
//    }

    @GetMapping(path = "/ordermethod")
    public ResponseEntity<List<OrderDTO>> getOrderByPaymentMethod(String user_id, String method){
        System.out.println(user_id);
        List<Orders> listOrder = orderService.findAllByPayment_Method(user_id,method);
        List<OrderDTO> listOrderDto = new ArrayList<>();
        for(Orders o: listOrder) {
            OrderDTO orderDto = modelMapper.map(o, OrderDTO.class);

            listOrderDto.add(orderDto);
        }
        for(Orders o: listOrder) {
            System.out.println(o.getId());
        }
        return new ResponseEntity<>(listOrderDto, HttpStatus.OK);
    }





}
