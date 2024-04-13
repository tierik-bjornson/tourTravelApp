package com.travelapp.TourTravel.repository;

import com.travelapp.TourTravel.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
    @Query(value="select * from customer c where c.id= :id and c.role=:role",nativeQuery = true)
    Customer findByIdAndRole(String id, String role);

    void deleteById(String id);
    Optional<Customer> findById(String id);
}
