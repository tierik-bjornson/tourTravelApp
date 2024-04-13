package com.travelapp.TourTravel.controller;

import com.travelapp.TourTravel.dto.TourDTO;
import com.travelapp.TourTravel.entity.Tour;
import com.travelapp.TourTravel.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tour")
public class TourController {

     TourService tourService;

    @Autowired
    public TourController(TourService tourService){
        this.tourService = tourService;
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<Tour>> searchTours(@RequestParam("searchContent") String searchContent) {
        List<Tour> matchingTours = tourService.findByTour_NameContaining(searchContent);

        if (matchingTours.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(matchingTours, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<Tour>> getAllTours() {
        List<Tour> tours = tourService.getAllTour();
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/{tourId}")
    public ResponseEntity<Tour> getTourById(@PathVariable int tourId) {
        Tour tour = tourService.getTourById(tourId);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tour> createTour(@RequestBody TourDTO tour) {
        Tour createdTour = tourService.saveTour(tour);
        return new ResponseEntity<>(createdTour, HttpStatus.CREATED);
    }


    @PutMapping("/{tourId}")
    public ResponseEntity<Tour> updateTour(@PathVariable int tourId, @RequestBody Tour tour) {
        Tour updatedTour = tourService.updateTours(tourId, tour);
        return new ResponseEntity<>(updatedTour, HttpStatus.OK);
    }






    @DeleteMapping("/{tourId}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long tourId) {
        tourService.deleteTourById(tourId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
