package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.dto.CustomerDTO;
import com.travelapp.TourTravel.entity.Customer;
import com.travelapp.TourTravel.repository.CustomerRepository;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{


    SessionFactory factory;
    ModelMapper modelMapper;

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }



    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer cus){
        return customerRepository.save(cus);
    }

    public CustomerDTO saveCustomers(CustomerDTO cusDTO){
        Customer customer = modelMapper.map(cusDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    public CustomerDTO getCustomer(String id) {
        return customerRepository.findById(id)
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .orElse(null);
    }

    public Customer updateCustomer (Customer cus){
        return  customerRepository.save(cus);
    }

    public void deleteByid(String id){
        customerRepository.deleteById(id);
    }

    public Customer getCusbyEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public Customer findByIdandRole(String id, String role){
        return customerRepository.findByIdAndRole(id,role);
    }


}
