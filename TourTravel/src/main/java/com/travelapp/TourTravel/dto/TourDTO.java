package com.travelapp.TourTravel.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class TourDTO {

    private String tourName;
    private String description;
    private int price;
    private int sold;
    private int size;
    private int discount;
    private List<TourImageDTO> tourImage;
    private List<CartDTO> cartDto;

}
