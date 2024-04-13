package com.travelapp.TourTravel.service;

import com.travelapp.TourTravel.entity.TourImage;

public interface TourImageService {
    void saveImage(TourImage image);
    void deleteImgaebyId(int id);
}
