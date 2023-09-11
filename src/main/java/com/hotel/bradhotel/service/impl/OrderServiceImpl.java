package com.hotel.bradhotel.service.impl;

import com.hotel.bradhotel.dao.OrderDao;
import com.hotel.bradhotel.dao.ProductDao;
import com.hotel.bradhotel.dto.BuyItem;
import com.hotel.bradhotel.dto.CreateOrderRequest;
import com.hotel.bradhotel.model.Order;
import com.hotel.bradhotel.model.OrderItem;
import com.hotel.bradhotel.model.Product;
import com.hotel.bradhotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount =0;
        List<OrderItem> orderItemList = new  ArrayList<>();


        for (BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            // 計算訂單總價錢
            int amoount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amoount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem= new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amoount);

            orderItemList .add(orderItem);

            System.out.println(totalAmount);
        }


        //創建訂單
        Integer orderId =orderDao.createOrder(userId, totalAmount);
        System.out.println("1");
        orderDao.createOrderItems(orderId, orderItemList);
        System.out.println("2");

        return orderId;
    }
}
