package com.example.inventario.backend.service;

import com.example.inventario.backend.dto.FacturaResponseDto;

public interface FacturaService {
    // Genera la factura: la guarda en BD y crea el archivo .txt
    FacturaResponseDto generarFactura(Long pedidoId);

    // Consulta una factura existente por su ID
    FacturaResponseDto obtenerFacturaPorId(Long facturaId);

    // Consulta una factura existente por el ID del pedido al que pertenece
    FacturaResponseDto obtenerFacturaPorPedidoId(Long pedidoId);
}
