package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.Cart;
import com.travelapp.TourTravel.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    CartRepository cartRepository;

    public void deleteById(int id){
        cartRepository.deleteById(id);
    }

    public Cart saveCart(Cart c){
        return cartRepository.save(c);
    }

    public List<Cart> getAllCartbyCusId(String CusId){
        return cartRepository.findAllByCusId(CusId);
    }

    public int getTourbyCartId (int id){
        return cartRepository.findTourByCartId(id);
    }
}
