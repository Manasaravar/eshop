package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Product;


public interface ProductService extends DAO<Product, Number> {
    Product findProductById (Long ProductId);

}


