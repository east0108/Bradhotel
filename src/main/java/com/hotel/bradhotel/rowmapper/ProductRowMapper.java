package com.hotel.bradhotel.rowmapper;

import com.hotel.bradhotel.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//讓查詢的商品資料表對應到java object
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setCategory(resultSet.getString("category"));
        product.setImageUrl(resultSet.getString("image_url"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreatedDate(resultSet.getTimestamp("created_date"));
        //Date要使用getTimestamp方法
        product.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        //Date要使用getTimestamp方法

        return product;
    }
}
