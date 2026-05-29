package com.example.inventario.backend.service.impl;

import com.example.inventario.backend.dto.PedidoItemRequestDto;
import com.example.inventario.backend.dto.PedidoRequestDto;
import com.example.inventario.backend.dto.PedidoResponseDto;
import com.example.inventario.backend.exception.ResourceNotFoundException;
import com.example.inventario.backend.exception.StockInsuficienteException;
import com.example.inventario.backend.mapper.PedidoMapper;
import com.example.inventario.backend.model.DetallePedido;
import com.example.inventario.backend.model.EstadoPedido;
import com.example.inventario.backend.model.Pedido;
import com.example.inventario.backend.model.Producto;
import com.example.inventario.backend.repository.PedidoRepository;
import com.example.inventario.backend.repository.ProductoRepository;
import com.example.inventario.backend.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public PedidoResponseDto crearPedido(PedidoRequestDto requestDto) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(requestDto.getClienteId());
        pedido.setFechaCreacion(LocalDateTime.now());

        BigDecimal totalPedido = BigDecimal.ZERO;

        for (PedidoItemRequestDto itemRequest : requestDto.getItems()) {
            Producto producto = productoRepository.findById(itemRequest.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + itemRequest.getProductoId()));

            // --- VALIDACIÓN DE STOCK ---
            // Verificamos que hay suficiente cantidad antes de continuar
            if (producto.getCantidadEnStock() < itemRequest.getCantidad()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para el producto '" + producto.getNombre() +
                        "'. Stock disponible: " + producto.getCantidadEnStock() +
                        ", cantidad solicitada: " + itemRequest.getCantidad()
                );
            }

            // --- DESCUENTO DE STOCK ---
            // Restamos la cantidad pedida del stock actual del producto
            producto.setCantidadEnStock(producto.getCantidadEnStock() - itemRequest.getCantidad());
            productoRepository.save(producto); // Persistimos el stock actualizado

            DetallePedido item = new DetallePedido();
            item.setPedido(pedido);
            item.setProducto(producto);
            item.setCantidad(itemRequest.getCantidad());
            item.setPrecioUnitario(producto.getPrecio()); // Tomas el precio actual de la BD

            BigDecimal cantidadBD = BigDecimal.valueOf(itemRequest.getCantidad());
            BigDecimal subtotal = producto.getPrecio().multiply(cantidadBD);
            totalPedido = totalPedido.add(subtotal);

            pedido.getItems().add(item);
        }

        pedido.setTotal(totalPedido);
        Pedido pedidoGuardado = pedidoRepository.save(pedido); // Cascade guarda los items

        return PedidoMapper.mapearAResponseDto(pedidoGuardado);
    }

    @Override
    public PedidoResponseDto obtenerPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + id));
        return PedidoMapper.mapearAResponseDto(pedido);
    }

    // --- MÉTODO PARA GESTOR DE PEDIDOS ---
    @Override
    @Transactional
    public PedidoResponseDto completarPedido(Long pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + pedidoId));

        // Cambiamos el estado a COMPLETADO y guardamos
        pedido.setEstado(EstadoPedido.COMPLETADO);
        Pedido pedidoActualizado = pedidoRepository.save(pedido);

        return PedidoMapper.mapearAResponseDto(pedidoActualizado);
    }
}
