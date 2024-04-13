package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.dto.TourDTO;
import com.travelapp.TourTravel.entity.Tour;
import com.travelapp.TourTravel.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }


    public List<Tour> getAllTour(){
        return tourRepository.findAll();
    }


    public Tour saveTour(TourDTO tourDTO){
        Tour tour = new Tour();
        tour.setTourName(tourDTO.getTourName());
        tour.setDescription(tourDTO.getDescription());
        tour.setPrice(tourDTO.getPrice());
        tour.setSize(tourDTO.getSize());
        tour.setDiscount(tourDTO.getDiscount());



        return tourRepository.save(tour);

    }

    public Tour getTourById(int id){
        return tourRepository.findById(id);
    }

    public Tour updateTour(Tour tour){
        return tourRepository.save(tour);
    }

    public void deleteTourById(Long id){
        tourRepository.deleteById(id);
    }

    public Page<Tour> findAllPage(Pageable pageable){
        return tourRepository.findAll(pageable);
    }
    public List<Tour> findByTour_NameContaining(String name) {

        return tourRepository.findByProduct_NameContaining(name);
    }

    @Transactional
    public Tour updateTours(int id, Tour updatedTour) {

        Tour existingTour = tourRepository.findById(id);

        if (existingTour != null) {
            existingTour.setTourName(updatedTour.getTourName());
            existingTour.setSize(updatedTour.getSize());
            existingTour.setPrice(updatedTour.getPrice());
            existingTour.setSold(updatedTour.getSold());

            existingTour.setDescription(updatedTour.getDescription());



            return tourRepository.save(existingTour);
        }


        return null;
    }








}
