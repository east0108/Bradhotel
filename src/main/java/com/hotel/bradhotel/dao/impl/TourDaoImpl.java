package com.hotel.bradhotel.dao.impl;

import com.hotel.bradhotel.dao.TourDao;
import com.hotel.bradhotel.dto.ProductQueryParams;
import com.hotel.bradhotel.dto.TourQuerParams;
import com.hotel.bradhotel.dto.TourRequest;
import com.hotel.bradhotel.model.Product;
import com.hotel.bradhotel.model.Tour;
import com.hotel.bradhotel.rowmapper.ProductRowMapper;
import com.hotel.bradhotel.rowmapper.TourRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TourDaoImpl implements TourDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer countTour(TourQuerParams tourQuerParams) {
        String sql = "SELECT count(*) FROM tour WHERE 1=1";

        Map<String, Object> map = new HashMap<>();
        //查詢條件
        sql = addFiltering(sql,map,tourQuerParams);

        Integer total =  namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);//Integer.class表示要返回類型

        return total;
    }

    @Override
    public List<Tour> getTours(TourQuerParams tourQuerParams) {
        String sql = "SElECT product_id, tour_name, city, address, tel, image_url, tickets, stock, introduce," +
                "created_date, last_modified_date " +
                "FROM tour WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();
        //查詢條件
        sql = addFiltering(sql,map,tourQuerParams);
        //排序
        sql = sql + " ORDER BY "+ tourQuerParams.getOrderBy() + " "+ tourQuerParams.getSort();
        //分頁
        sql = sql + " LIMIT  :limit OFFSET :offset";
        map.put("limit", tourQuerParams.getLimit());
        map.put("offset", tourQuerParams.getOffset());

        //ORDER BY的SQL語法只能用字串拼接的方式，不能用SQL變數實作
        //前後要預留空白建
        List<Tour> tourList = namedParameterJdbcTemplate.query(sql, map, new TourRowMapper());

        return tourList;
    }

    @Override
    public Tour getTourById(Integer productId) {
        String sql = "SElECT product_id, tour_name, city, address, tel, image_url, tickets, stock, introduce," +
                "created_date, last_modified_date " +
                "FROM tour WHERE product_id = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Tour> tourList = namedParameterJdbcTemplate.query(sql, map, new TourRowMapper());

        if(tourList.size() > 0){
            return tourList.get(0);
        }else {
            return null;
        }


    }

    @Override
    public Integer createTour(TourRequest tourRequest) {
        String sql = "INSERT INTO tour(tour_name, city, address, tel, image_url, tickets, stock, introduce," +
                "created_date, last_modified_date) " +
                "VALUES(:tourName, :city,:address, :tel, :imageUrl, :tickets, :stock, :introduce," +
                " :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("tourName", tourRequest.getTourName());
        map.put("city", tourRequest.getCity().toString());
        map.put("address", tourRequest.getAddress());
        map.put("tel", tourRequest.getTel());
        map.put("imageUrl", tourRequest.getImageUrl());
        map.put("tickets", tourRequest.getTickets());
        map.put("stock", tourRequest.getStock());
        map.put("introduce", tourRequest.getIntroduce());

        //由程式生成時間，并將時間記錄到created_date與last_modified_date兩個資料庫欄位中
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql , new MapSqlParameterSource(map), keyHolder);

        int tourId = keyHolder.getKey().intValue();

        return tourId;
    }

    @Override
    public void updateTour(Integer productId, TourRequest tourRequest) {

        String sql = "UPDATE tour SET tour_name = :tourName, city = :city, address = :address, tel = :tel, image_url = :imageUrl," +
                "tickets = :tickets, stock= :stock, introduce= :introduce," +
                "last_modified_date= :lastModifiedDate " +
                "WHERE product_id = :productID";

        Map<String, Object> map = new HashMap<>();
        map.put("productID", productId);

        map.put("tourName", tourRequest.getTourName());
        map.put("city", tourRequest.getCity().toString());
        map.put("address", tourRequest.getAddress());
        map.put("tel", tourRequest.getTel());
        map.put("imageUrl", tourRequest.getImageUrl());
        map.put("tickets", tourRequest.getTickets());
        map.put("stock", tourRequest.getStock());
        map.put("introduce", tourRequest.getIntroduce());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql ,map);
    }

    @Override
    public void deleteTourById(Integer productId) {
        String sql = "DELETE FROM tour WHERE product_id = :productID";

        Map<String, Object> map = new HashMap<>();
        map.put("productID", productId);

        namedParameterJdbcTemplate.update(sql ,map);
    }


    private String addFiltering(String sql, Map<String, Object> map, TourQuerParams tourQuerParams){

        //查詢語句一定要去判斷是否為null
        //查詢條件
        if (tourQuerParams.getCity() != null){
            sql = sql + " AND city = :city ";//要預留空白建
            map.put("city", tourQuerParams.getCity().name());//使用enum類型的name方法將enum類型轉換成字串
        }

        if (tourQuerParams.getSearch() != null){
            sql = sql + " AND tour_name LIKE :search ";//要預留空白建
            map.put("search", "%" + tourQuerParams.getSearch() + "%");//模糊查詢一定要寫在map裡面，不能寫在sql語句
        }

        return sql;
    }

}
