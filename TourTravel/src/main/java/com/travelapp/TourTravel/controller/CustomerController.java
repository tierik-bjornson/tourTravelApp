package com.travelapp.TourTravel.controller;

import com.google.gson.Gson;
import com.travelapp.TourTravel.dto.CustomerDTO;
import com.travelapp.TourTravel.dto.Mail;
import com.travelapp.TourTravel.dto.TourDTO;
import com.travelapp.TourTravel.entity.Customer;
import com.travelapp.TourTravel.entity.Tour;
import com.travelapp.TourTravel.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    HttpSession session;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    MailService mailService;



    @GetMapping(path = "/login")
    public ResponseEntity<Customer> Login(String id, String password) {
        System.out.println(id);
        Customer userFind = customerService.findByIdandRole(id, "customer");

        if (userFind != null && userFind.getPassword() != null) {
            String decodedValue = new String(Base64.getDecoder().decode(userFind.getPassword()));
            System.out.println(userFind);
            if (password.equals(decodedValue)) {
//			userFind.setPassword(decodedValue);
                userFind.setPassword(decodedValue);
                return new ResponseEntity<>(userFind, HttpStatus.OK);
            }
            else {
                return null;
            }
        }
        else
            return new ResponseEntity<>(userFind, HttpStatus.OK);
    }

    @PostMapping(path = "/signup", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Customer> SignUp(String username, String Cusname, String email, String password) {
        Customer user = customerService.findByIdandRole(username, "customer");
        if (user != null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());

            Customer newUser = customerService.saveCustomer(new Customer(username, Cusname, "customer", null, email, encodedValue, null, null, null));
            System.out.println(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/forgotnewpass", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> ForgotNewPass(String id, String code, String password) {
//		String codeSession = (String) session.getAttribute("code");
//		System.out.println("session: "+ codeSession);
        Customer user = customerService.findByIdandRole(id, "customer");
        if (user != null) {
            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
            user.setPassword(encodedValue);
            customerService.saveCustomer(user);
            return new ResponseEntity<String>(password, HttpStatus.OK);
        } else
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(path = "changepassword", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> ChangePassword(String id, String password) {
        Customer user = customerService.findByIdandRole(id, "customer");
        if (user != null) {
            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
            user.setPassword(encodedValue);
            customerService.saveCustomer(user);
            return new ResponseEntity<String>(password, HttpStatus.OK);
        } else
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(path = "update", consumes = "multipart/form-data")
    public ResponseEntity<Customer> Update(String id, String Cusname, String email,
                                           String phoneNumber) {
        Customer user = customerService.findByIdandRole(id, "customer");
        if (user != null) {

            user.setCusname(Cusname);
            user.setEmail(email);
            user.setPhone(phoneNumber);

            customerService.saveCustomer(user);
            if(user.getPassword()!=null)
                user.setPassword(new String(Base64.getDecoder().decode(user.getPassword())));
            return new ResponseEntity<Customer>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(path = "google", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<Customer> LoginWithGoogle(String id, String Cusname, String email) {
        Customer user = customerService.findByIdandRole(id, "customer");
        if (user == null) {
            user = customerService
                    .saveCustomer(new Customer(id, Cusname, "customer", null, email, null, null, null, null));
        }
        return new ResponseEntity<Customer>(user, HttpStatus.OK);
    }

    @PostMapping(path = "/forgot", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> ForgotPassword(String id) {
        Customer user = customerService.findByIdandRole(id, "customer");
        if (user != null) {
            int code = (int) Math.floor(((Math.random() * 899999) + 100000));
            Mail mail = new Mail();
            mail.setMailFrom("bjornson25102003@gmail.com");
            mail.setMailTo(user.getEmail());
            mail.setMailSubject("Forgot Password");
            mail.setMailContent("Your code is: " + code);

            mailService.sendEmail(mail);
            session.setAttribute("code", code);
            System.out.println(code);
            return new ResponseEntity<String>(new Gson().toJson(String.valueOf(code)), HttpStatus.OK);
        } else
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllcustomers() {
        List<Customer> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getTourById(@PathVariable String customerId) {
        CustomerDTO customers = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping

    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomers(customerDTO);
    }

}
