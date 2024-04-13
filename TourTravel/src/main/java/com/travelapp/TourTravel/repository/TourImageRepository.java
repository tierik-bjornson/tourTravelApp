package com.travelapp.TourTravel.repository;

import com.travelapp.TourTravel.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourImageRepository extends JpaRepository<TourImage, Integer> {
}
