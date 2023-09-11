package com.hotel.bradhotel.dao;

import com.hotel.bradhotel.dto.CreateOrderRequest;
import com.hotel.bradhotel.model.Order;
import com.hotel.bradhotel.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void  createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
