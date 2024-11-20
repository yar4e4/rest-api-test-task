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

public class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;

    @BeforeEach
    public void setup() {
        productService = mock(ProductService.class);  // Мокаем интерфейс
        productController = new ProductController(productService);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        Product createdProduct = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        when(productService.addProduct(product)).thenReturn(createdProduct);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Товар 1", response.getBody().getName());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Товар 1", response.getBody().getName());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product(1L, "Товар 1", "Описание товара", 100.0, true);
        when(productService.deleteProduct(1L)).thenReturn(true);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productService.getProductById(999L)).thenReturn(Optional.empty());

        try {
            productController.getProductById(999L);
            fail("ProductNotFoundException should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof ProductNotFoundException);
        }
    }
}