package com.example.inventario.backend.controller;

import com.example.inventario.backend.dto.FacturaResponseDto;
import com.example.inventario.backend.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    // Actor: Gestor de Pedidos
    // POST /api/facturas/generar/{pedidoId}
    // Genera la factura para un pedido y guarda el archivo .txt en el servidor
    @PostMapping("/generar/{pedidoId}")
    public ResponseEntity<FacturaResponseDto> generarFactura(@PathVariable Long pedidoId) {
        FacturaResponseDto factura = facturaService.generarFactura(pedidoId);
        return new ResponseEntity<>(factura, HttpStatus.CREATED);
    }

    // Actor: Gestor de Pedidos
    // GET /api/facturas/{id}
    // Consulta una factura por su propio ID
    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponseDto> consultarFacturaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtenerFacturaPorId(id));
    }

    // GET /api/facturas/pedido/{pedidoId}
    // Consulta la factura asociada a un pedido específico
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<FacturaResponseDto> consultarFacturaPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(facturaService.obtenerFacturaPorPedidoId(pedidoId));
    }
}
