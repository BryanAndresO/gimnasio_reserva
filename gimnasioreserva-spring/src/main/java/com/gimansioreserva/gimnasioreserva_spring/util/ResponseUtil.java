package com.gimansioreserva.gimnasioreserva_spring.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    /**
     * Crea una respuesta exitosa genérica
     */
    public static <T> ResponseEntity<Map<String, Object>> success(T data) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", data);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    /**
     * Crea una respuesta exitosa con mensaje
     */
    public static <T> ResponseEntity<Map<String, Object>> success(T data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    /**
     * Crea una respuesta exitosa sin datos
     */
    public static ResponseEntity<Map<String, Object>> success(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }

    /**
     * Crea una respuesta de creación exitosa (201)
     */
    public static <T> ResponseEntity<Map<String, Object>> created(T data, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", message);
        response.put("data", data);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Crea una respuesta de error
     */
    public static ResponseEntity<Map<String, Object>> error(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }

    /**
     * Crea una respuesta de error con detalles
     */
    public static ResponseEntity<Map<String, Object>> error(String message, Object details, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", message);
        response.put("errors", details);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(status).body(response);
    }

    /**
     * Crea una respuesta de error 400 Bad Request
     */
    public static ResponseEntity<Map<String, Object>> badRequest(String message) {
        return error(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Crea una respuesta de error 400 Bad Request con detalles
     */
    public static ResponseEntity<Map<String, Object>> badRequest(String message, Object details) {
        return error(message, details, HttpStatus.BAD_REQUEST);
    }

    /**
     * Crea una respuesta de error 401 Unauthorized
     */
    public static ResponseEntity<Map<String, Object>> unauthorized(String message) {
        return error(message, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Crea una respuesta de error 403 Forbidden
     */
    public static ResponseEntity<Map<String, Object>> forbidden(String message) {
        return error(message, HttpStatus.FORBIDDEN);
    }

    /**
     * Crea una respuesta de error 404 Not Found
     */
    public static ResponseEntity<Map<String, Object>> notFound(String message) {
        return error(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Crea una respuesta de error 409 Conflict
     */
    public static ResponseEntity<Map<String, Object>> conflict(String message) {
        return error(message, HttpStatus.CONFLICT);
    }

    /**
     * Crea una respuesta de error 500 Internal Server Error
     */
    public static ResponseEntity<Map<String, Object>> internalError(String message) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Crea una respuesta paginada
     */
    public static <T> ResponseEntity<Map<String, Object>> paginated(
            T data,
            int page,
            int size,
            long totalElements,
            int totalPages) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", page);
        pagination.put("size", size);
        pagination.put("totalElements", totalElements);
        pagination.put("totalPages", totalPages);
        
        response.put("success", true);
        response.put("data", data);
        response.put("pagination", pagination);
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}

