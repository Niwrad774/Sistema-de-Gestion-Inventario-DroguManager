package com.example.inventario.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Se lanza cuando se intenta pedir más cantidad de la que hay en stock
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }
}
