package com.hotel.bradhotel.service.impl;

import com.hotel.bradhotel.dao.OrderDao;
import com.hotel.bradhotel.dao.TourDao;
import com.hotel.bradhotel.dao.UserDao;
import com.hotel.bradhotel.dto.BuyItem;
import com.hotel.bradhotel.dto.CreateOrderRequest;
import com.hotel.bradhotel.dto.OrderQueryParams;
import com.hotel.bradhotel.model.Order;
import com.hotel.bradhotel.model.OrderItem;
import com.hotel.bradhotel.model.Tour;
import com.hotel.bradhotel.model.User;
import com.hotel.bradhotel.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TourDao tourDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
      List<Order> orderList = orderDao.getOrders(orderQueryParams);

      for (Order order : orderList){
          List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(order.getOrderId());

          order.setOrderItemList(orderItemList);
      }


        return orderList;
    }

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

        User user = userDao.getUserById(userId);
        //檢查使用者是否存在
        if (user ==null){
            log.warn("該userId {} 不存在",userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount =0;
        List<OrderItem> orderItemList = new  ArrayList<>();


        for (BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Tour tour = tourDao.getTourById(buyItem.getProductId());

            //檢查 product是否存在，庫存是否足夠
            if(tour ==null){
                log.warn("該商品 {} 不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if(tour.getStocks() < buyItem.getQuantity() ){
                log.warn("商品 {} 庫存數量不足，無法購買，剩餘庫存 {} ，欲購買數量 {}",
                        buyItem.getProductId(),tour.getStocks(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //扣除庫存
            tourDao.updateStock(tour.getProductId(),tour.getStocks()-buyItem.getQuantity());



            // 計算訂單總價錢
            int amoount = buyItem.getQuantity() * tour.getTickets();
            totalAmount = totalAmount + amoount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem= new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amoount);

            orderItemList .add(orderItem);


        }


        //創建訂單
        Integer orderId =orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);


        return orderId;
    }
}
