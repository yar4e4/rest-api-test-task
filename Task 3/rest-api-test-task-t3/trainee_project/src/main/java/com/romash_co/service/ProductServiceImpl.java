package com.romash_co.service;

import com.romash_co.model.Product;
import com.romash_co.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реализация интерфейса {@link ProductService}.
 * Выполняет операции с объектами {@link Product}, используя {@link ProductRepository}.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Конструктор для внедрения зависимости {@link ProductRepository}.
     *
     * @param productRepository Репозиторий для работы с товарами.
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id); // Устанавливаем ID для обновления существующего товара
            return Optional.of(productRepository.save(updatedProduct));
        }
        return Optional.empty(); // Если товар не найден, возвращаем пустой Optional
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id); // Удаляем товар
            return true;
        }
        return false; // Товар не найден
    }
}