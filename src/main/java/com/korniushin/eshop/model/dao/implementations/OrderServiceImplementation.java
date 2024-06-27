package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.dao.repositories.OrderRepository;

import com.korniushin.eshop.model.dao.repositories.ProductRepository;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.OrderPosition;
import com.korniushin.eshop.model.entities.OrderStatus;
import com.korniushin.eshop.model.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    private Set<Product> products = new HashSet<>();

    @Override
    public Order save(Order order) {
        order.setCreated(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        order.setUpdated(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> all() {
        return orderRepository.findAll();
    }

    @Override
    public void pay(Long id) {
        Order orderPaid = orderRepository.findById(id).get();
        orderPaid.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(orderPaid);
        Order orderCart = Order.builder()
                .user(orderPaid.getUser())
                .address("")
                .created(LocalDateTime.now())
                .orderStatus(OrderStatus.CART)
                .updated(LocalDateTime.now())
                .totalPrice(0.0)
                .totalQuantity(0)
                .productsPositions(new HashSet<>())
                .build();
        orderRepository.save(orderCart);
    }

    @Override
    public void addPosition(Order order, Product product, Integer quantity) {
        OrderPosition orderPosition = OrderPosition.builder()
                .orderQuantity(quantity)
                .order(order)
                .product(product)
                .build();
        order.getProductsPositions().add(orderPosition);
        order.setUpdated(LocalDateTime.now());
        orderRepository.save(order);

        product.setQuantity(product.getQuantity() - quantity);

        productRepository.save(product);
    }

    @Override
    public void delPosition(Order order, Product product, Integer quantity) {

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        if (product.getQuantity() > quantity) {
            order.setTotalQuantity(order.getTotalQuantity() - quantity);
            order.setUpdated(LocalDateTime.now());
            orderRepository.save(order);

        } else
            orderRepository.deleteById(order.getId());
    }

    @Override
    public Order findOrderByUserId(Long id) {
       return orderRepository.findOrderByUserId(id);
        }

}
