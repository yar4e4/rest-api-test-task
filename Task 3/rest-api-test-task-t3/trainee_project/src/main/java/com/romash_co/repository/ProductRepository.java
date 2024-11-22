package com.romash_co.repository;

import com.romash_co.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностью {@link Product}.
 *
 * Предоставляет методы для выполнения CRUD-операций над объектами {@link Product},
 * а также дополнительные методы, которые можно определить при необходимости.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Дополнительные методы можно добавить здесь
}