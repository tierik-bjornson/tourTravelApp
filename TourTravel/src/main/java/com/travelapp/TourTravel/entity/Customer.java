package com.travelapp.TourTravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")

public class Customer {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;
    @Column(name="Cus_name")
    private String Cusname;
    @Column(name="role")
    private String role;
    @Column(name="phone_number")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="login_type")
    private String logintype;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> order;

}
