package com.romash_co.controller;

import com.romash_co.exception.ProductNotFoundException;
import com.romash_co.model.Product;
import com.romash_co.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Контроллер для управления товарами.
 */
@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Управление товарами: создание, получение, обновление и удаление товаров.")
public class ProductController {

    private final ProductService productService;

    /**
     * Конструктор контроллера товаров.
     * @param productService Сервис для обработки операций с товарами.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Получить список всех товаров.
     * @return Список объектов {@link Product}.
     */
    @GetMapping
    @Operation(summary = "Получить все товары", description = "Возвращает список всех доступных товаров.")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Получить товар по идентификатору.
     * @param id Идентификатор товара.
     * @return Объект {@link Product}, если товар найден.
     * @throws ProductNotFoundException Если товар с указанным ID не найден.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Получить товар по ID", description = "Возвращает информацию о товаре по его идентификатору.")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ProductNotFoundException("Товар с id " + id + " не найден"));
    }

    /**
     * Создать новый товар.
     * @param product Объект {@link Product}, представляющий новый товар.
     * @return Созданный объект {@link Product}.
     */
    @PostMapping
    @Operation(summary = "Создать товар", description = "Добавляет новый товар в систему.")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Обновить информацию о товаре.
     * @param id Идентификатор товара для обновления.
     * @param product Объект {@link Product} с обновленными данными.
     * @return Обновленный объект {@link Product}, если товар найден.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Обновить товар", description = "Обновляет информацию о товаре по его идентификатору.")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Удалить товар по идентификатору.
     * @param id Идентификатор товара.
     * @return HTTP статус 204 (No Content), если удаление успешно; 404 (Not Found), если товар не найден.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить товар", description = "Удаляет товар из системы по его идентификатору.")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}