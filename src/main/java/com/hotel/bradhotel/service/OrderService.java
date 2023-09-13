package com.hotel.bradhotel.service;

import com.hotel.bradhotel.dto.CreateOrderRequest;
import com.hotel.bradhotel.dto.OrderQueryParams;
import com.hotel.bradhotel.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);



    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
