package com.hotel.bradhotel.service;

import com.hotel.bradhotel.constant.ProductCategory;
import com.hotel.bradhotel.dto.ProductRequest;
import com.hotel.bradhotel.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
