package com.korniushin.eshop.model.dao.repositories;

import com.korniushin.eshop.model.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
