package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.TourQuerParams;
import com.hotel.bradhotel.dto.TourRequest;
import com.hotel.bradhotel.model.Tour;

import java.util.List;

public interface TourService {

    Integer countTour(TourQuerParams tourQuerParams);

    List<Tour> getTours(TourQuerParams tourQuerParams);

    Tour getTourById(Integer productId);

    Integer createTour(TourRequest tourRequest);

    void updateTour(Integer productId,TourRequest tourRequest);

    void deleteTourById(Integer productId);

}
