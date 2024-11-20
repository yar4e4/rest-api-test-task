package com.romash_co.service;

import com.romash_co.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    public void setup() {
        // Мокируем интерфейс ProductService
        productService = mock(ProductService.class);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(null, "Товар 1", "Описание товара 1", 100.0, true);
        Product createdProduct = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Мокаем поведение метода addProduct
        when(productService.addProduct(product)).thenReturn(createdProduct);

        Product result = productService.addProduct(product);

        assertNotNull(result.getId());
        assertEquals("Товар 1", result.getName());
        assertEquals(100.0, result.getPrice());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Товар 1", response.getBody().getName());
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);
        Product product2 = new Product(2L, "Товар 2", "Описание товара 2", 200.0, false);

        // Мокаем метод getAllProducts
        when(productService.getAllProducts()).thenReturn(List.of(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара 1", 100.0, true);

        // Мокаем метод deleteProduct
        when(productService.deleteProduct(1L)).thenReturn(true);

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
    }
}