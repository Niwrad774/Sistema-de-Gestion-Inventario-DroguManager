package com.example.inventario.backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemRequestDto {
    private Long productoId;
    private Integer cantidad;
}
