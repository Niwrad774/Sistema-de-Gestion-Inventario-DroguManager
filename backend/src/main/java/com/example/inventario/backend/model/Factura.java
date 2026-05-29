package com.example.inventario.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
@Data
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación 1 a 1: cada factura pertenece a un único pedido
    // @JoinColumn indica cuál es la columna de clave foránea en la tabla "facturas"
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

    private LocalDateTime fechaEmision;

    // Total copiado del pedido al momento de emitir la factura
    private BigDecimal totalFacturado;

    // Ruta al archivo .txt generado en el servidor (ej: "facturas/factura_1.txt")
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
}
