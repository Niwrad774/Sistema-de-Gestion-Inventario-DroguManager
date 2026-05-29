package com.example.inventario.backend.mapper;

import com.example.inventario.backend.dto.PedidoItemResponseDto;
import com.example.inventario.backend.dto.PedidoResponseDto;
import com.example.inventario.backend.model.DetallePedido;
import com.example.inventario.backend.model.Pedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {
    public static PedidoResponseDto mapearAResponseDto(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoResponseDto dto = new PedidoResponseDto();
        dto.setId(pedido.getId());
        dto.setClienteId(pedido.getClienteId());
        dto.setFechaCreacion(pedido.getFechaCreacion());
        dto.setTotal(pedido.getTotal());
        dto.setEstado(pedido.getEstado()); // nuevo campo

        if (pedido.getItems() != null) {
            List<PedidoItemResponseDto> itemsDto = pedido.getItems().stream()
                    .map(PedidoMapper::mapearItemDto)
                    .collect(Collectors.toList());
            dto.setItems(itemsDto);
        }

        return dto;
    }

    // Método estático privado
    private static PedidoItemResponseDto mapearItemDto(DetallePedido item) {
        if (item == null) {
            return null;
        }

        PedidoItemResponseDto dto = new PedidoItemResponseDto();

        dto.setProductoId(item.getProducto().getId());
        dto.setNombreProducto(item.getProducto().getNombre());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        
        // Convertir cantidad a BigDecimal para multiplicar correctamente con el precio
        BigDecimal cantidadBD = BigDecimal.valueOf(item.getCantidad());
        BigDecimal subtotal = item.getPrecioUnitario().multiply(cantidadBD);
        dto.setSubtotal(subtotal);

        return dto;
    }
}
