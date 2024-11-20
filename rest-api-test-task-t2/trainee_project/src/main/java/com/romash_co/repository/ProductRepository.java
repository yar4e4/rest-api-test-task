package com.romash_co.repository;

import com.romash_co.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Дополнительные методы, если нужны
}