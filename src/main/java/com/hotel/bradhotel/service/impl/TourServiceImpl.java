package com.hotel.bradhotel.service.impl;

import com.hotel.bradhotel.dao.TourDao;
import com.hotel.bradhotel.dto.TourQuerParams;
import com.hotel.bradhotel.dto.TourRequest;
import com.hotel.bradhotel.model.Tour;
import com.hotel.bradhotel.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TourServiceImpl implements TourService{

    @Autowired
    private TourDao tourDao;

    @Override
    public Integer countTour(TourQuerParams tourQuerParams) {
        return tourDao.countTour(tourQuerParams);
    }

    @Override
    public List<Tour> getTours(TourQuerParams tourQuerParams) {
        return tourDao.getTours(tourQuerParams);
    }

    @Override
    public Integer createTour(TourRequest tourRequest) {

        return tourDao.createTour(tourRequest);
    }

    @Override
    public Tour getTourById(Integer productId) {
        return tourDao.getTourById(productId);
    }

    @Override
    public void updateTour(Integer productId, TourRequest tourRequest) {
        tourDao.updateTour(productId,tourRequest);
    }

    @Override
    public void deleteTourById(Integer productId) {
        tourDao.deleteTourById(productId);
    }
}
