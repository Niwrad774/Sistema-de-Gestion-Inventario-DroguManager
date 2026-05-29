package com.example.inventario.backend.dto;

import com.example.inventario.backend.model.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// DTO de respuesta para cuando se consulta o genera una factura
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaResponseDto {
    private Long facturaId;
    private Long pedidoId;
    private LocalDateTime fechaEmision;
    private BigDecimal totalFacturado;
    private String rutaArchivo;   // Para que el cliente sepa dónde quedó guardado el .txt

    // Incluimos el resumen del pedido directamente para no hacer otra consulta desde el frontend
    private Long clienteId;
    private EstadoPedido estadoPedido;
    private List<PedidoItemResponseDto> itemsPedido;
}
