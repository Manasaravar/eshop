package com.korniushin.eshop.model.dao.interfaces.baseInterface;

import java.util.List;
import java.util.Optional;

public interface DAO<T, L extends Number> {
    List<T> all();
    Optional<T> findById(Long id);
    T save(T t);
    T update(T t);
    boolean deleteById(Long id);
}
