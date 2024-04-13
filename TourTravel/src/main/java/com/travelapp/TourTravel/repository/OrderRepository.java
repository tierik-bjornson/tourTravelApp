package com.travelapp.TourTravel.repository;

import com.travelapp.TourTravel.entity.Orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository

public interface OrderRepository extends JpaRepository<Orders,Integer> {
    Orders findById(int id);
    Page<Orders> findAll(Pageable pageable);

    @Query(value="select * from orders o where o.payment_method = ?1",nativeQuery = true)
    List<Orders> findAllByPayment_Method(String payment_Method);
    @Query(value="SELECT * FROM orders o   WHERE o.Cus_Id = ?1", nativeQuery = true)
    List<Orders> findAllByCusId(String cusId);

    @Query(value="select * from `order` o where o.payment_method = ?1 and o.user_id = ?2",nativeQuery = true)
    List<Orders> findAllByPayment_Methods(String payment_Method, String cus_id);



}
