package com.romash_co.controller;

import com.romash_co.model.Product;
import java.util.Optional;
import com.romash_co.service.ProductService;
import com.romash_co.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для контроллера {@link ProductController}.
 * Используются моки для сервиса {@link ProductService} и тестируются методы контроллера.
 */
public class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    /**
     * Метод инициализации, выполняемый перед каждым тестом.
     * Мокаем сервис {@link ProductService} и создаем экземпляр {@link ProductController}.
     */
    @BeforeEach
    public void setup() {
        productService = mock(ProductService.class);  // Мокаем интерфейс
        productController = new ProductController(productService);
    }

    /**
     * Тестирование метода {@link ProductController#createProduct(Product)}.
     * Проверяется, что продукт создается успешно и возвращается статус CREATED.
     */
    @Test
    public void testCreateProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        Product createdProduct = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        when(productService.addProduct(product)).thenReturn(createdProduct);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());  // Проверка статуса ответа
        assertEquals("Товар 1", response.getBody().getName());  // Проверка имени продукта
    }

    /**
     * Тестирование метода {@link ProductController#getProductById(Long)}.
     * Проверяется, что продукт с заданным ID найден и возвращен со статусом OK.
     */
    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());  // Проверка статуса ответа
        assertEquals("Товар 1", response.getBody().getName());  // Проверка имени продукта
    }

    /**
     * Тестирование метода {@link ProductController#deleteProduct(Long)}.
     * Проверяется, что товар удаляется успешно и возвращается статус NO_CONTENT.
     */
    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        when(productService.deleteProduct(1L)).thenReturn(true);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());  // Проверка статуса ответа
    }

    /**
     * Тестирование метода {@link ProductController#getProductById(Long)} на случай, когда продукт не найден.
     * Ожидается, что будет выброшено исключение {@link ProductNotFoundException}.
     */
    @Test
    public void testGetProductById_NotFound() {
        when(productService.getProductById(999L)).thenReturn(Optional.empty());

        try {
            productController.getProductById(999L);
            fail("ProductNotFoundException should be thrown");  // Ожидаем исключение
        } catch (Exception e) {
            assertTrue(e instanceof ProductNotFoundException);  // Проверка типа исключения
        }
    }
}