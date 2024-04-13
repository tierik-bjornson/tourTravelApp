package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.dto.TourDTO;
import com.travelapp.TourTravel.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TourService {
    List<Tour> getAllTour();
    Tour saveTour(TourDTO tourDTO);
    Tour getTourById(int id);
    Tour updateTour(Tour tour);
    void deleteTourById(Long id);
    Page<Tour> findAllPage(Pageable pageable);
    List<Tour> findByTour_NameContaining(String name);
    Tour updateTours(int id, Tour tour);
}
