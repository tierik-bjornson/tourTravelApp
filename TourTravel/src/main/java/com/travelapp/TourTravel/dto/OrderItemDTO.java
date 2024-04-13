package com.travelapp.TourTravel.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class OrderItemDTO {
    private int id;
    private int count;
    private TourDTO tour;

}
