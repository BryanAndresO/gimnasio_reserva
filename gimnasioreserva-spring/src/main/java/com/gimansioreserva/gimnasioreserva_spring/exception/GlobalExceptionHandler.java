package com.gimansioreserva.gimnasioreserva_spring.exception;

import com.gimansioreserva.gimnasioreserva_spring.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException e) {
        return ResponseUtil.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CupoAgotadoException.class)
    public ResponseEntity<?> handleCupoAgotadoException(CupoAgotadoException e) {
        return ResponseUtil.error(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ReservaDuplicadaException.class)
    public ResponseEntity<?> handleReservaDuplicadaException(ReservaDuplicadaException e) {
        return ResponseUtil.error(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ClaseNoDisponibleException.class)
    public ResponseEntity<?> handleClaseNoDisponibleException(ClaseNoDisponibleException e) {
        return ResponseUtil.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TiempoMinimoException.class)
    public ResponseEntity<?> handleTiempoMinimoException(TiempoMinimoException e) {
        return ResponseUtil.error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<?> handleReservaNoEncontradaException(ReservaNoEncontradaException e) {
        return ResponseUtil.error(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseUtil.error("Error de validación", errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        return ResponseUtil.error("Error interno del servidor: " + e.getMessage(), 
                                 HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

