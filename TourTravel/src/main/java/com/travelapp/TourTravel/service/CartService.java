package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.Cart;

import java.util.List;

public interface CartService {
    void deleteById(int id);
    Cart saveCart(Cart c);
    List<Cart> getAllCartbyCusId(String CusId);
    int getTourbyCartId (int id);

}
