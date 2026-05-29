package com.example.inventario.backend.dto;

import com.example.inventario.backend.model.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// Versión actualizada con el campo "estado" del pedido
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponseDto {
    private Long id;
    private Long clienteId;
    private LocalDateTime fechaCreacion;
    private BigDecimal total;
    private EstadoPedido estado;
    private List<PedidoItemResponseDto> items;
}
