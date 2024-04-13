package com.travelapp.TourTravel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total")
    private int total;

    @Column(name = "booking_date")
    private Date booking_date;

    @Column(name = "payment_method", columnDefinition = "nvarchar(1111)")
    private String payment_method;

    @Column(name = "status", columnDefinition = "nvarchar(1111)")
    private String status;

    @Column(name = "fullname", columnDefinition = "nvarchar(1111)")
    private String fullname;

    @Column(name = "address", columnDefinition = "nvarchar(1111)")
    private String address;

    @Column(name = "phone", columnDefinition = "nvarchar(1111)")
    private String phone;

    @Column(name = "email", columnDefinition = "nvarchar(1111)")
    private String email;

    @Column(name = "note", columnDefinition = "nvarchar(1111)")
    private String note;

    @ManyToOne
    @JoinColumn(name = "cus_id")  // Thay đổi tên cột kết nối
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Customer customer;  // Thay đổi tên thuộc tính

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;  // Thay đổi tên thuộc tính

}
