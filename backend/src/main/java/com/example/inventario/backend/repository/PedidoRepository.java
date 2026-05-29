package com.example.inventario.backend.repository;

import com.example.inventario.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
