package com.romash_co.service;

import com.romash_co.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Product addProduct(Product product);

    Optional<Product> updateProduct(Long id, Product updatedProduct);

    boolean deleteProduct(Long id);
}