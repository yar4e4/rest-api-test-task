package com.romash_co.exception;

/**
 * Исключение, которое выбрасывается, когда запрашиваемый продукт не найден.
 */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Создает исключение с заданным сообщением об ошибке.
     *
     * @param message Сообщение об ошибке, описывающее причину исключения.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}