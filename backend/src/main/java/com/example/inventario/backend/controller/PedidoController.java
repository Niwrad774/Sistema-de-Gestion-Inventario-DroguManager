package com.example.inventario.backend.controller;

import com.example.inventario.backend.dto.PedidoRequestDto;
import com.example.inventario.backend.dto.PedidoResponseDto;
import com.example.inventario.backend.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    // Actor: Comprador
    @PostMapping
    public ResponseEntity<PedidoResponseDto> hacerPedido(@RequestBody PedidoRequestDto request) {
        return new ResponseEntity<>(pedidoService.crearPedido(request), HttpStatus.CREATED);
    }

    // Actor: Comprador / Gestor de Pedidos
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> consultarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerPedidoPorId(id));
    }

    // Actor: Gestor de Pedidos
    // PATCH es el verbo correcto para actualizar solo un campo (el estado)
    @PatchMapping("/{id}/completar")
    public ResponseEntity<PedidoResponseDto> completarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.completarPedido(id));
    }
}
