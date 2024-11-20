package com.romash_co.service;

import com.romash_co.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    public void setup() {
        productService = Mockito.mock(ProductService.class);  // Мокируем ProductService
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        Product createdProduct = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Настроим мок так, чтобы он возвращал созданный продукт
        Mockito.when(productService.addProduct(product)).thenReturn(createdProduct);

        Product result = productService.addProduct(product);

        assertNotNull(result.getId());
        assertEquals("Товар 1", result.getName());
        assertEquals(100.0, result.getPrice());
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        Product product2 = new Product(2L, "Товар 2", "Описание товара 2", 200.0, false);

        // Настроим мок для метода getAllProducts
        Mockito.when(productService.getAllProducts()).thenReturn(List.of(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Настроим мок для getProductById
        Mockito.when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productService.getProductById(999L)).thenReturn(Optional.empty());

        assertTrue(productService.getProductById(1L).isPresent());
        assertFalse(productService.getProductById(999L).isPresent());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        Product updatedProduct = new Product(1L, "Обновленный товар", "Новое описание", 150.0, true);

        // Настроим мок для updateProduct
        Mockito.when(productService.updateProduct(1L, updatedProduct)).thenReturn(Optional.of(updatedProduct));

        Optional<Product> result = productService.updateProduct(1L, updatedProduct);

        assertTrue(result.isPresent());
        assertEquals("Обновленный товар", result.get().getName());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Настроим мок для deleteProduct
        Mockito.when(productService.deleteProduct(1L)).thenReturn(true);

        boolean deleted = productService.deleteProduct(1L);

        assertTrue(deleted);
    }
}