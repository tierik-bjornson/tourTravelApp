package com.travelapp.TourTravel.repository;

import com.travelapp.TourTravel.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value="select cus_id from tourtravelg6.cart where id = ?",nativeQuery = true)
    List<Cart> findAllByCusId(String CusId);
    @Query(value="select tourId from tourtravelg6.cart where id = ?",nativeQuery = true)
    int findTourByCartId(int id);
}
