package com.hotel.bradhotel.dao;

import com.hotel.bradhotel.dto.CreateOrderRequest;
import com.hotel.bradhotel.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void  createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
