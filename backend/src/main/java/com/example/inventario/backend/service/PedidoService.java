package com.example.inventario.backend.service;

import com.example.inventario.backend.dto.PedidoRequestDto;
import com.example.inventario.backend.dto.PedidoResponseDto;

public interface PedidoService {
    PedidoResponseDto crearPedido(PedidoRequestDto requestDto);
    PedidoResponseDto obtenerPedidoPorId(Long id);

    // Método para que el Gestor de Pedidos cambie el estado a COMPLETADO
    PedidoResponseDto completarPedido(Long pedidoId);
}
