package com.example.inventario.backend.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemResponseDto {
    private Long productoId;
    private String nombreProducto; // Útil para el frontend
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}
