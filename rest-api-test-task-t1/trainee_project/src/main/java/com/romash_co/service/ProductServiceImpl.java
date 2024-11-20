package com.romash_co.service;

import com.romash_co.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>();
    private long nextId = 1;

    // Получить все товары
    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Возвращаем копию для защиты
    }

    // Найти товар по ID
    @Override
    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    // Добавить новый товар
    @Override
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    // Обновить существующий товар
    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return getProductById(id).map(existingProduct -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setInStock(updatedProduct.isInStock());
            return existingProduct;
        });
    }

    // Удалить товар
    @Override
    public boolean deleteProduct(Long id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}