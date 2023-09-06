package com.hotel.bradhotel.dao.impl;

import com.hotel.bradhotel.dao.ProductDao;
import com.hotel.bradhotel.dto.ProductQueryParams;
import com.hotel.bradhotel.dto.ProductRequest;
import com.hotel.bradhotel.model.Product;
import com.hotel.bradhotel.rowmapper.ProductRowMapper;
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
public class productDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SElECT product_id, product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date " +
                "FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();
        //查詢語句一定要去判斷是否為null
        if (productQueryParams.getCategory() != null){
            sql = sql + " AND category = :category";//要預留空白建
            map.put("category", productQueryParams.getCategory().name());//使用enum類型的name方法將enum類型轉換成字串
        }

        if (productQueryParams.getSearch() != null){
            sql = sql + " AND product_name LIKE :search";//要預留空白建
            map.put("search", "%" + productQueryParams.getSearch() + "%");//模糊查詢一定要寫在map裡面，不能寫在sql語句
        }

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    @Override//對應參數在rowmapper內
    public Product getProductById(Integer productId) {
        String sql = "SElECT product_id, product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date " +
                "FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size() > 0){
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override //對應參數在ProductRequest內
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date) " +
                "VALUES(:productName, :category, :imageUrl, :price, :stock, :description," +
                " :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        //由程式生成時間，并將時間記錄到created_date與last_modified_date兩個資料庫欄位中
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql , new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }


    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {

        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl," +
                "price= :price, stock= :stock, description= :description," +
                "last_modified_date= :lastModifiedDate " +
                "WHERE product_id = :productID";

        Map<String, Object> map = new HashMap<>();
        map.put("productID", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql ,map);

    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productID";

        Map<String, Object> map = new HashMap<>();
        map.put("productID", productId);

        namedParameterJdbcTemplate.update(sql ,map);
    }
}

