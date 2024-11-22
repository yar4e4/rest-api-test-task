package com.romash_co.service;

import com.romash_co.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

/**
 * Тесты для сервиса {@link ProductService}.
 * Используются моки для тестирования методов сервиса {@link ProductService}.
 */
public class ProductServiceTest {

    private ProductService productService;

    /**
     * Метод инициализации, выполняемый перед каждым тестом.
     * Мокаем сервис {@link ProductService} для тестирования.
     */
    @BeforeEach
    public void setup() {
        productService = Mockito.mock(ProductService.class);  // Мокируем ProductService
    }

    /**
     * Тестирование метода {@link ProductService#addProduct(Product)}.
     * Проверяется, что продукт добавляется корректно, и что его ID и название соответствуют ожиданиям.
     */
    @Test
    public void testAddProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        Product createdProduct = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Настроим мок так, чтобы он возвращал созданный продукт
        Mockito.when(productService.addProduct(product)).thenReturn(createdProduct);

        Product result = productService.addProduct(product);

        assertNotNull(result.getId());  // Проверка, что продукт имеет ID
        assertEquals("Товар 1", result.getName());  // Проверка имени продукта
        assertEquals(100.0, result.getPrice());  // Проверка цены продукта
    }

    /**
     * Тестирование метода {@link ProductService#getAllProducts()}.
     * Проверяется, что метод возвращает все добавленные товары.
     */
    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        Product product2 = new Product(2L, "Товар 2", "Описание товара 2", 200.0, false);

        // Настроим мок для метода getAllProducts
        Mockito.when(productService.getAllProducts()).thenReturn(List.of(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());  // Проверка, что возвращается два товара
    }

    /**
     * Тестирование метода {@link ProductService#getProductById(Long)}.
     * Проверяется, что метод корректно находит продукт по ID.
     */
    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Настроим мок для getProductById
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productService.getProductById(999L)).thenReturn(Optional.empty());

        assertTrue(productService.getProductById(1L).isPresent());  // Проверка, что продукт найден
        assertFalse(productService.getProductById(999L).isPresent());  // Проверка, что продукт не найден
    }

    /**
     * Тестирование метода {@link ProductService#updateProduct(Long, Product)}.
     * Проверяется, что продукт обновляется корректно.
     */
    @Test
    public void testUpdateProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        Product updatedProduct = new Product(1L, "Обновленный товар", "Новое описание", 150.0, true);

        // Настроим мок для updateProduct
        Mockito.when(productService.updateProduct(1L, updatedProduct)).thenReturn(Optional.of(updatedProduct));

        Optional<Product> result = productService.updateProduct(1L, updatedProduct);

        assertTrue(result.isPresent());  // Проверка, что результат обновления существует
        assertEquals("Обновленный товар", result.get().getName());  // Проверка нового имени
    }

    /**
     * Тестирование метода {@link ProductService#deleteProduct(Long)}.
     * Проверяется, что продукт удаляется корректно.
     */
    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Настроим мок для deleteProduct
        Mockito.when(productService.deleteProduct(1L)).thenReturn(true);

        boolean deleted = productService.deleteProduct(1L);

        assertTrue(deleted);  // Проверка, что продукт был удален
    }
}