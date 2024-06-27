package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Product;

import java.util.List;


public interface ProductService extends DAO<Product, Number> {
    Product findProductById (Long ProductId);


    //поиск
    List<Product> listAll(String keyword);

}


