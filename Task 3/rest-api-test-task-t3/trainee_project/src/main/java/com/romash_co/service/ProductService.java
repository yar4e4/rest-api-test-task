package com.romash_co.service;

import com.romash_co.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Сервисный интерфейс для управления товарами.
 * Определяет операции для работы с объектами {@link Product}.
 */
public interface ProductService {

    /**
     * Возвращает список всех товаров.
     *
     * @return Список объектов {@link Product}.
     */
    List<Product> getAllProducts();

    /**
     * Находит товар по его уникальному идентификатору.
     *
     * @param id Идентификатор товара.
     * @return {@link Optional}, содержащий объект {@link Product}, если товар найден; иначе пустой {@link Optional}.
     */
    Optional<Product> getProductById(Long id);

    /**
     * Добавляет новый товар.
     *
     * @param product Объект {@link Product}, который нужно добавить.
     * @return Созданный объект {@link Product}.
     */
    Product addProduct(Product product);

    /**
     * Обновляет информацию о существующем товаре.
     *
     * @param id            Идентификатор товара для обновления.
     * @param updatedProduct Объект {@link Product} с обновлёнными данными.
     * @return {@link Optional}, содержащий обновлённый объект {@link Product}, если товар найден; иначе пустой {@link Optional}.
     */
    Optional<Product> updateProduct(Long id, Product updatedProduct);

    /**
     * Удаляет товар по его идентификатору.
     *
     * @param id Идентификатор товара.
     * @return true, если товар успешно удалён; false, если товар с таким идентификатором не найден.
     */
    boolean deleteProduct(Long id);
}