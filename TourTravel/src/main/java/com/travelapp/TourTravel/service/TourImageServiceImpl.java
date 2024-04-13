package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.TourImage;
import com.travelapp.TourTravel.repository.TourImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourImageServiceImpl implements TourImageService{
    @Autowired
    TourImageRepository tourImageRepository;

    public void saveImage(TourImage image){
        tourImageRepository.save(image);
    }

    public void deleteImgaebyId(int id){
        tourImageRepository.deleteById(id);
    }


}
