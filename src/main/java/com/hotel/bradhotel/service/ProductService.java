package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.ProductQueryParams;
import com.hotel.bradhotel.dto.ProductRequest;
import com.hotel.bradhotel.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);



}
