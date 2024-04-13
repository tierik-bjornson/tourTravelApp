package com.travelapp.TourTravel.controller;

import com.travelapp.TourTravel.dto.Mail;
import com.travelapp.TourTravel.entity.Tour;
import com.travelapp.TourTravel.entity.Customer;
import com.travelapp.TourTravel.entity.Orders;
import com.travelapp.TourTravel.entity.OrderItem;
import com.travelapp.TourTravel.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    TourService tourService;
    @Autowired
    CustomerService customerService;
    @Autowired
    TourImageService tourImageService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    HttpSession session;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    MailService mailService;

    @GetMapping("/signin-admin")
    public String SignInAdminView(Model model) {
        String err_sign_admin = (String) session.getAttribute("err_sign_admin");
        model.addAttribute("err_sign_admin", err_sign_admin);
        session.setAttribute("err_sign_admin", null);
        return "signin-admin";
    }

    @PostMapping("/signin-admin")
    public String SignInAdminHandel(@ModelAttribute("login-name") String login_name,
                                    @ModelAttribute("pass") String pass, Model model) throws Exception {
        Customer admin = customerService.findByIdandRole("admin", "admin");
        System.out.println(admin);
        if (admin == null) {
            session.setAttribute("err_sign_admin", "Username or Password is not correct!");
            return "redirect:/signin-admin";
        } else {
//            String decodedValue = new String(Base64.getDecoder().decode(admin.getPassword()));
//            if (!decodedValue.equals(pass)) {
//                session.setAttribute("err_sign_admin", "Username or Password is not correct!");
//                return "redirect:/signin-admin";
            if (!admin.getPassword().equals(pass)) {
                session.setAttribute("err_sign_admin", "Username or Password is not correct!");
                return "redirect:/signin-admin";
            } else {
                System.out.println(admin);
                session.setAttribute("admin", admin);
                return "redirect:/dashboard";
            }
        }
    }


    @GetMapping("/logout-admin")
    public String LogOutAdmin(Model model) {
        session.setAttribute("admin", null);
        return "redirect:/signin-admin";
    }

    @GetMapping("/dashboard")
    public String DashboardView(Model model) {
        Customer admin = (Customer) session.getAttribute("admin");
        System.out.println("======");
        if (admin == null) {
            return "redirect/signin-admin";
        } else {
            List<Orders> listOrder = orderService.GetAllOrder();
            List<Tour> listTour = tourService.getAllTour();
            List<Customer> listCustomer = customerService.getAllCustomer();


            model.addAttribute("Total_Order", listOrder.size());
            model.addAttribute("Total_Tour", listTour.size());
            model.addAttribute("Total_Customer", listCustomer.size());

            return "dashboard";
        }
    }

    @GetMapping("/dashboard-orders")
    public String DashboardOrderView(Model model) {
        Customer admin = (Customer) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/signin-admin.html";
        } else {
            Pageable pageable = PageRequest.of(0, 3);
            Page<Orders> pageOrder = orderService.findAll(pageable);
            model.addAttribute("pageOrder", pageOrder);
            return "dashboard-orders";
        }
    }


    @GetMapping("/dashboard-orders/{page}")
    public String DashboardOrderPageView(@PathVariable int page, Model model) {
        Customer admin = (Customer) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/signin-admin";
        } else {
            Pageable pageable = PageRequest.of(page, 3);
            Page<Orders> pageOrder = orderService.findAll(pageable);
            model.addAttribute("pageOrder", pageOrder);
            return "dashboard-orders";
        }
    }

    @PostMapping("/send-message")
    public String SendMessage(Model model, @ModelAttribute("message") String message,
                              @ModelAttribute("email") String email, HttpServletRequest request) throws Exception {
        String referer = request.getHeader("Referer");
        System.out.println(message);
        System.out.println(email);
        Mail mail = new Mail();
        mail.setMailFrom("bjornson25102003@gmail.com");
        mail.setMailTo(email);
        mail.setMailSubject("This is message from TourTravel TLU.");
        mail.setMailContent(message);
        mailService.sendEmail(mail);
        return "redirect:" + referer;
    }

    @GetMapping("/delete-order/{id}")
    public String DeleteOrder(@PathVariable int id, Model model, HttpServletRequest request) throws Exception {
        Customer admin = (Customer) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/signin-admin";
        } else {
            String referer = request.getHeader("Referer");
            Orders order = orderService.findOrderById(id);
            System.out.println(order);
            if (order != null) {
                for (OrderItem y : order.getOrderItems()) {
                    orderItemService.deleteOrderItemId(y.getId());
                }
                orderService.deleteOrderById(id);
            }
            return "redirect:" + referer;
        }
    }

    @GetMapping("/redirect")
    public String Redirect(Model model, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/dashboard-invoice/{id}")
    public String InvoiceView(@PathVariable int id, Model model, HttpServletRequest request) {
        Orders order = orderService.findOrderById(id);
        List<OrderItem> listOrder_Item = orderItemService.getAllByOrderId(order.getId());
        model.addAttribute("listOrder_Item", listOrder_Item);
        model.addAttribute("order", order);
        return "dashboard-invoice";
    }

    @GetMapping("dashboard-wallet")
    public String DashboardWalletView(Model model) {
        Customer admin = (Customer) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/signin-admin";
        } else {
            List<Orders> listOrder = orderService.GetAllOrder();
            List<Orders> listPaymentWithVNPay = orderService.findAllOrderByPaymentMethod("Pay with VNPay");
            List<Orders> listPaymentOnDelivery = orderService.findAllOrderByPaymentMethod("Pay on Delivery");
            int TotalVNPay = 0;
            int TotalDelivery = 0;
            for (Orders y : listPaymentWithVNPay) {
                TotalVNPay = TotalVNPay + y.getTotal();
            }
            for (Orders y : listPaymentOnDelivery) {
                TotalDelivery = TotalDelivery + y.getTotal();
            }
            model.addAttribute("TotalVNPay", TotalVNPay);
            model.addAttribute("TotalDelivery", TotalDelivery);
            model.addAttribute("TotalOrder", listOrder.size());
            return "dashboard-wallet";
        }
    }




















}
