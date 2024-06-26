package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.Product;





public interface OrderService extends DAO<Order, Long> {

    void pay(Long id);
    void addPosition(Order order, Product product, Integer quantity);
    void delPosition(Order order, Product product, Integer quantity);
    Order findOrderByUserId (Long id);

}
