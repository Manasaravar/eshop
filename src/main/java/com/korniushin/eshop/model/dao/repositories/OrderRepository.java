package com.korniushin.eshop.model.dao.repositories;

import com.korniushin.eshop.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByUserId (Long id);
}
