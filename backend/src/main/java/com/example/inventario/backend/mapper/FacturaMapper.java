package com.example.inventario.backend.mapper;

import com.example.inventario.backend.dto.FacturaResponseDto;
import com.example.inventario.backend.dto.PedidoItemResponseDto;
import com.example.inventario.backend.model.DetallePedido;
import com.example.inventario.backend.model.Factura;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

// Clase utilitaria estática, igual que ProductMapper.
// Convierte una entidad Factura a su DTO de respuesta.
public class FacturaMapper {

    public static FacturaResponseDto mapearAResponseDto(Factura factura) {
        if (factura == null) {
            return null;
        }

        // Mapear los items del pedido al DTO que ya existe
        List<PedidoItemResponseDto> itemsDto = factura.getPedido().getItems().stream()
                .map(FacturaMapper::mapearItemDto)
                .collect(Collectors.toList());

        return new FacturaResponseDto(
                factura.getId(),
                factura.getPedido().getId(),
                factura.getFechaEmision(),
                factura.getTotalFacturado(),
                factura.getRutaArchivo(),
                factura.getPedido().getClienteId(),
                factura.getPedido().getEstado(),
                itemsDto
        );
    }

    // Reutilizamos la misma lógica de mapeo de items que en PedidoMapper
    private static PedidoItemResponseDto mapearItemDto(DetallePedido item) {
        if (item == null) {
            return null;
        }

        BigDecimal cantidadBD = BigDecimal.valueOf(item.getCantidad());
        BigDecimal subtotal = item.getPrecioUnitario().multiply(cantidadBD);

        PedidoItemResponseDto dto = new PedidoItemResponseDto();
        dto.setProductoId(item.getProducto().getId());
        dto.setNombreProducto(item.getProducto().getNombre());
        dto.setCantidad(item.getCantidad());
        dto.setPrecioUnitario(item.getPrecioUnitario());
        dto.setSubtotal(subtotal);

        return dto;
    }
}
