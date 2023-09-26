package com.hotel.bradhotel.rowmapper;

import com.hotel.bradhotel.model.Tour;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//讓查詢的商品資料表對應到java object
public class TourRowMapper implements RowMapper<Tour> {


    @Override
    public Tour mapRow(ResultSet resultSet, int i) throws SQLException {
        Tour tour = new Tour();

        tour.setProductId(resultSet.getInt("product_id"));

        tour.setTourName(resultSet.getString("tour_name"));

        tour.setCity(resultSet.getString("city"));

        tour.setAddress(resultSet.getString("address"));

        tour.setTel(resultSet.getString("tel"));

        tour.setImageUrl(resultSet.getString("image_url"));

        tour.setTickets(resultSet.getInt("tickets"));

        tour.setStocks(resultSet.getInt("stock"));

        tour.setIntroduce(resultSet.getString("introduce"));

        //Date要使用getTimestamp方法
        tour.setCreatedDate(resultSet.getTimestamp("created_date"));
        //Date要使用getTimestamp方法
        tour.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return tour;
    }
}
