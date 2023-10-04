package com.hotel.bradhotel.controller;

import com.hotel.bradhotel.constant.TourCity;
import com.hotel.bradhotel.dto.TourQuerParams;
import com.hotel.bradhotel.dto.TourRequest;
import com.hotel.bradhotel.model.Tour;
import com.hotel.bradhotel.service.TourService;
import com.hotel.bradhotel.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")//required = false 表示非必要，讓參事表示可選擇
    public ResponseEntity<Page<Tour>> getTours(
            //查詢條件 Filtering
            @RequestParam(required = false) TourCity city,
            @RequestParam(required = false) String search,

            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁 Pagination
            @RequestParam(defaultValue = "6") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
            ){
        TourQuerParams tourQuerParams = new TourQuerParams();
        tourQuerParams.setCity(city);
        tourQuerParams.setSearch(search);
        tourQuerParams.setOrderBy(orderBy);
        tourQuerParams.setSort(sort);
        tourQuerParams.setLimit(limit);
        tourQuerParams.setOffset(offset);


        //取得Tourlist
        List<Tour> tourList = tourService.getTours(tourQuerParams);
        //取得product 總數
        Integer total = tourService.countTour(tourQuerParams);

        //分頁
        Page<Tour> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(tourList);

        return ResponseEntity.status(HttpStatus.OK).body(page);




    }

    @GetMapping("/tour/{productId}")
    public ResponseEntity<Tour> getTour(@PathVariable Integer productId){
      Tour tour =  tourService.getTourById(productId);

      if (tour !=  null) {
          return ResponseEntity.status(HttpStatus.OK).body(tour);
      }else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
    }

    @PostMapping("/tour")
    public ResponseEntity<Tour> createTour(@RequestBody @Valid TourRequest tourRequest){
        Integer productId = tourService.createTour(tourRequest);

       Tour tour = tourService.getTourById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(tour);
    }

    @PutMapping("/tour/{productId}")
    public ResponseEntity<Tour> updateTour(@PathVariable  Integer productId,
                                           @RequestBody @Valid TourRequest tourRequest){
        //檢查product是否存在
        Tour tour = tourService.getTourById(productId);

        if (tour ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改商品數據
        tourService.updateTour(productId,tourRequest);

        Tour updateTour = tourService.getTourById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateTour);
    }

    @DeleteMapping("/tour/{productId}")
    public ResponseEntity<?> deleteTour(@PathVariable Integer productId){
        tourService.deleteTourById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
