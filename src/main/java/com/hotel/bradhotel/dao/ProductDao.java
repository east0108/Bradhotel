package com.hotel.bradhotel.dao;

import com.hotel.bradhotel.dto.ProductRequest;
import com.hotel.bradhotel.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
