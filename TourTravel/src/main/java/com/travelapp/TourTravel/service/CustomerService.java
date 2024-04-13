package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.dto.CustomerDTO;
import com.travelapp.TourTravel.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomer();
    CustomerDTO saveCustomers(CustomerDTO cusDTO);
    Customer saveCustomer(Customer cus);
    CustomerDTO getCustomer(String id);
    Customer updateCustomer (Customer cus);
    void deleteByid(String id);
    Customer getCusbyEmail(String email);
     Customer findByIdandRole(String id, String role);
}
