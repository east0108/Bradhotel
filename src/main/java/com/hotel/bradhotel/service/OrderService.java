package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.CreateOrderRequest;
import com.hotel.bradhotel.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
