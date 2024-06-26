package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.interfaces.ProductService;
import com.korniushin.eshop.model.dao.repositories.ProductRepository;
import com.korniushin.eshop.model.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    final private ProductRepository productRepository;

    @Override
    public List<Product> all() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product newProduct = productRepository.findById(product.getId()).get();
        newProduct.setBrand(product.getBrand());
        newProduct.setCategory(product.getCategory());
        newProduct.setColor(product.getColor());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setUnit(product.getUnit());

        return productRepository.save(newProduct);

    }

    @Override
    public boolean deleteById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


    @Override
    public Product findProductById(Long ProductId) {
        return productRepository.findProductById(ProductId);
    }
}
