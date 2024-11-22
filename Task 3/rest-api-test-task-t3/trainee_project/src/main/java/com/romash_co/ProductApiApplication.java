package com.romash_co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения для запуска Spring Boot.
 * Это точка входа в приложение, которая инициализирует Spring контекст и запускает сервер.
 */
@SpringBootApplication(scanBasePackages = "com.romash_co")
public class ProductApiApplication {

    /**
     * Точка входа в приложение.
     * Запускает Spring Boot приложение, используя {@link SpringApplication}.
     *
     * @param args Аргументы командной строки, передаваемые в приложение.
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }
}