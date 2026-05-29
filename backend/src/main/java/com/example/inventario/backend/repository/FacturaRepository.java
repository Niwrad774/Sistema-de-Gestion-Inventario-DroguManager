package com.example.inventario.backend.repository;

import com.example.inventario.backend.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // Busca una factura dado el ID del pedido asociado
    Optional<Factura> findByPedidoId(Long pedidoId);
}
