package com.example.inventario.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidadEnStock;
}
