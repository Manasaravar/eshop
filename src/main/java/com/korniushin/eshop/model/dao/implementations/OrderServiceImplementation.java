package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.interfaces.OrderService;
import com.korniushin.eshop.model.dao.repositories.OrderRepository;

import com.korniushin.eshop.model.dao.repositories.ProductRepository;
import com.korniushin.eshop.model.entities.Order;
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
                .products(new HashSet<>())
                .build();
        orderRepository.save(orderCart);
    }

    @Override
    public void addPosition(Order order, Product product, Integer quantity) {
        final Product productToOrder = Product.builder()
                .color(product.getColor())
                .brand(product.getBrand())
                .price(product.getPrice())
                .composition(product.getComposition())
                .unit(product.getUnit())
                .article(product.getArticle())
                .category(product.getCategory())
                .order(order)
                .quantity(quantity)
                .build();
        order.getProducts().add(productToOrder);
        order.setUpdated(LocalDateTime.now());
        orderRepository.save(order);

        productToOrder.setQuantity(productToOrder.getQuantity() - quantity);

        productRepository.save(productToOrder);
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
