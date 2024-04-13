package com.travelapp.TourTravel.repository;

import com.travelapp.TourTravel.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
    @Query(value="select * from tour p where p.tourName like %?1%",nativeQuery = true)
    List<Tour> findByProduct_NameContaining(String name);

    Tour findById(int id);



}
