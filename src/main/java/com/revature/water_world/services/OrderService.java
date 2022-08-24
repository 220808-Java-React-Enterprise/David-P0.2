package com.revature.water_world.services;
import com.revature.water_world.models.Order;
import com.revature.water_world.daos.*;

import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void saveOrder(Order ord) {orderDAO.saveOrd(ord);}


    public List<Order> getAllOrdersByAccountId(String id) {
        return orderDAO.getAllByAccountId(id);
    }
}
