package com.travelapp.TourTravel.dto;

import lombok.Data;

@Data
public class CartDTO {
    private int id;
    private int count;
    private TourDTO tour;
    private CustomerDTO customer;
}
