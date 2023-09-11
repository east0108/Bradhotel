package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
