package com.travelapp.TourTravel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.Order;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders_item")

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "tourId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Orders order;
}
