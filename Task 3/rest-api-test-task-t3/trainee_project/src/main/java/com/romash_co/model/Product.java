package com.romash_co.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Модель сущности товара.
 * Представляет данные о товаре, которые сохраняются в базе данных.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название товара не может быть пустым")
    @Size(max = 255, message = "Название товара не может превышать 255 символов")
    private String name;

    @Size(max = 4096, message = "Описание товара не может превышать 4096 символов")
    private String description;

    @Min(value = 0, message = "Цена товара не может быть меньше 0")
    private double price = 0.0;

    private boolean inStock = false;

    /**
     * Конструктор по умолчанию.
     */
    public Product() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param id          Уникальный идентификатор товара.
     * @param name        Название товара.
     * @param description Описание товара.
     * @param price       Цена товара.
     * @param inStock     Наличие товара в наличии (true - в наличии, false - отсутствует).
     */
    public Product(Long id, String name, String description, double price, boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    /**
     * Возвращает уникальный идентификатор товара.
     *
     * @return ID товара.
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор товара.
     *
     * @param id ID товара.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название товара.
     *
     * @return Название товара.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название товара.
     *
     * @param name Название товара.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает описание товара.
     *
     * @return Описание товара.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание товара.
     *
     * @param description Описание товара.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает цену товара.
     *
     * @return Цена товара.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Устанавливает цену товара.
     *
     * @param price Цена товара.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Проверяет, есть ли товар в наличии.
     *
     * @return true, если товар в наличии; false в противном случае.
     */
    public boolean isInStock() {
        return inStock;
    }

    /**
     * Устанавливает наличие товара в наличии.
     *
     * @param inStock true, если товар в наличии; false в противном случае.
     */
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}