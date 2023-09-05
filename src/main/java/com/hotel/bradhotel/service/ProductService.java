package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.ProductRequest;
import com.hotel.bradhotel.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

}
