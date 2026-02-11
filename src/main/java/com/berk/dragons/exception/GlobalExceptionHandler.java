package com.berk.dragons.exception; // <--- НОВЫЙ ПАКЕТ

import com.berk.dragons.patterns.SystemLogger; // <--- ОБЯЗАТЕЛЬНО ИМПОРТИРУЙ
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // Логируем ошибку через наш Singleton
        SystemLogger.getInstance().error("Exception caught: " + ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKey(org.springframework.dao.DuplicateKeyException ex) {
        SystemLogger.getInstance().error("Database Conflict: " + ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Dragon with this name already exists!"); // Понятное сообщение
        body.put("status", HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT); // Возвращаем 409
    }
}