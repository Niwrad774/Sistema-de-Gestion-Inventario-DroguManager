package com.example.inventario.backend.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDto {
    private Long clienteId;
    private List<PedidoItemRequestDto> items;
}
