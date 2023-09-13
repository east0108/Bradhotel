package com.hotel.bradhotel.dao;

import com.hotel.bradhotel.dto.CreateOrderRequest;
import com.hotel.bradhotel.dto.OrderQueryParams;
import com.hotel.bradhotel.model.Order;
import com.hotel.bradhotel.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);


    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void  createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
