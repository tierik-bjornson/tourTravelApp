package com.travelapp.TourTravel.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CustomerDTO {
    private String id;
    private String login_type;
    private String role;
    private String phone;
    private String email;
    private String password;
    private String Cusname;


}
