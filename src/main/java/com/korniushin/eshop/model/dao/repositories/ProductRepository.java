package com.korniushin.eshop.model.dao.repositories;
import com.korniushin.eshop.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    Product findProductById (Long ProductId);
}
