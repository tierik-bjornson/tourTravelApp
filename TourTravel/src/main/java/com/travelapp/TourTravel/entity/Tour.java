package com.travelapp.TourTravel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table (name="tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="tourName")
    private String tourName;
    @Column(name="descriptions")
    private String description;
    @Column(name="price")
    private int price;
    @Column(name="sold")
    private int sold;
    @Column(name="size")
    private int size;
    @Column(name="discount")
    private int discount;
}
