package com.hotel.bradhotel.service.impl;

import com.hotel.bradhotel.dao.ProductDao;
import com.hotel.bradhotel.model.Product;
import com.hotel.bradhotel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
