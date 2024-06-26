package com.korniushin.eshop.model.dao.repositories;

import com.korniushin.eshop.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository <User, Long> {
   Optional<User> findByUsername(String name);
}
