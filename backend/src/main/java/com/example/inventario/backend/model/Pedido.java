package com.example.inventario.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId; // O una relación @ManyToOne a Cliente si tienes esa entidad
    private LocalDateTime fechaCreacion;
    private BigDecimal total;

    // @Enumerated(STRING) guarda el nombre del enum ("PENDIENTE") en lugar de su número (0).
    // Esto es mucho más legible en la base de datos.
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    // CascadeType.ALL es vital: al guardar un Pedido, se guardan sus items automáticamente.
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> items = new ArrayList<>();
}
