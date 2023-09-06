package com.hotel.bradhotel.dao;

import com.hotel.bradhotel.dto.ProductRequest;
import com.hotel.bradhotel.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
