package com.travelapp.TourTravel.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class OrderDTO {
    private int id;
    private int total;
    private Date booking_date;
    private String note;
    private String email;
    private String fullname;
    private String address;
    private String phone;
    private String payment_method;
    private String status;
    private CustomerDTO customer;
    private List<OrderItemDTO> orderItem;


}
