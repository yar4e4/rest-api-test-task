package com.romash_co.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Глобальный обработчик исключений для всего приложения.
 * Перехватывает и обрабатывает общие и пользовательские исключения.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключения {@link MethodArgumentNotValidException}, возникающие при ошибках валидации.
     *
     * @param ex Исключение {@link MethodArgumentNotValidException}.
     * @return Ответ с HTTP статусом 400 (Bad Request) и подробной информацией об ошибках.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())); // Добавляем ошибки валидации
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Обрабатывает исключения {@link ProductNotFoundException}, возникающие при попытке получить несуществующий продукт.
     *
     * @param ex Исключение {@link ProductNotFoundException}.
     * @return Ответ с HTTP статусом 404 (Not Found) и сообщением об ошибке.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage()); // Сообщение об отсутствии товара
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
}