package com.hotel.bradhotel.dao;

import com.hotel.bradhotel.dto.TourQuerParams;
import com.hotel.bradhotel.dto.TourRequest;
import com.hotel.bradhotel.model.Tour;

import java.util.List;

public interface TourDao {

    Integer countTour(TourQuerParams tourQuerParams);

    List<Tour> getTours(TourQuerParams tourQuerParams);

    Tour getTourById(Integer productId);

    Integer createTour(TourRequest tourRequest);

    void updateTour(Integer productId,TourRequest tourRequest);

    void updateStock(Integer productId, Integer stock);

    void deleteTourById(Integer productId);
}
