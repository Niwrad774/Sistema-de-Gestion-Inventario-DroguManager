package com.example.inventario.backend.service.impl;

import com.example.inventario.backend.dto.FacturaResponseDto;
import com.example.inventario.backend.exception.ResourceNotFoundException;
import com.example.inventario.backend.mapper.FacturaMapper;
import com.example.inventario.backend.model.Factura;
import com.example.inventario.backend.model.Pedido;
import com.example.inventario.backend.repository.FacturaRepository;
import com.example.inventario.backend.repository.PedidoRepository;
import com.example.inventario.backend.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final PedidoRepository pedidoRepository;

    // Carpeta donde se guardarán los archivos .txt de facturas
    // En una app real, esta ruta vendría de application.properties
    private static final String CARPETA_FACTURAS = "facturas/";

    @Override
    @Transactional
    public FacturaResponseDto generarFactura(Long pedidoId) {
        // 1. Verificar que el pedido existe
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con id: " + pedidoId));

        // 2. Verificar que no se haya generado ya una factura para ese pedido
        if (facturaRepository.findByPedidoId(pedidoId).isPresent()) {
            throw new IllegalStateException("Ya existe una factura para el pedido con id: " + pedidoId);
        }

        // 3. Crear el registro de Factura en la base de datos
        Factura factura = new Factura();
        factura.setPedido(pedido);
        factura.setFechaEmision(LocalDateTime.now());
        factura.setTotalFacturado(pedido.getTotal());

        // 4. Generar el archivo .txt en el servidor
        String rutaArchivo = generarArchivoTxt(pedido, factura.getFechaEmision());
        factura.setRutaArchivo(rutaArchivo);

        // 5. Guardar la factura en la BD con la ruta del archivo
        Factura facturaGuardada = facturaRepository.save(factura);

        return FacturaMapper.mapearAResponseDto(facturaGuardada);
    }

    @Override
    public FacturaResponseDto obtenerFacturaPorId(Long facturaId) {
        Factura factura = facturaRepository.findById(facturaId)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id: " + facturaId));
        return FacturaMapper.mapearAResponseDto(factura);
    }

    @Override
    public FacturaResponseDto obtenerFacturaPorPedidoId(Long pedidoId) {
        Factura factura = facturaRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe factura para el pedido con id: " + pedidoId));
        return FacturaMapper.mapearAResponseDto(factura);
    }

    @Override
    public List<FacturaResponseDto> listarFacturas() {
        return facturaRepository.findAll().stream()
                .map(FacturaMapper::mapearAResponseDto)
                .collect(Collectors.toList());
    }

    // --- Método privado: genera el archivo .txt de la factura ---
    private String generarArchivoTxt(Pedido pedido, LocalDateTime fechaEmision) {
        try {
            // Crear la carpeta "facturas/" si no existe
            Files.createDirectories(Paths.get(CARPETA_FACTURAS));

            String nombreArchivo = CARPETA_FACTURAS + "factura_pedido_" + pedido.getId() + ".txt";
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            // PrintWriter facilita escribir línea por línea en el archivo
            try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
                writer.println("========================================");
                writer.println("       DROGUERÍA - FACTURA              ");
                writer.println("========================================");
                writer.println("Fecha de emisión : " + fechaEmision.format(formato));
                writer.println("Número de Pedido : " + pedido.getId());
                writer.println("Cliente ID       : " + pedido.getClienteId());
                writer.println("Estado del Pedido: " + pedido.getEstado());
                writer.println("----------------------------------------");
                writer.println("DETALLE DE PRODUCTOS:");
                writer.println("----------------------------------------");

                // Iterar sobre los items del pedido para listar cada producto
                for (var item : pedido.getItems()) {
                    BigDecimal cantidadBD = BigDecimal.valueOf(item.getCantidad());
                    BigDecimal subtotal = item.getPrecioUnitario().multiply(cantidadBD);

                    writer.printf("  - %-25s x%d  @ $%-10.2f Subtotal: $%.2f%n",
                            item.getProducto().getNombre(),
                            item.getCantidad(),
                            item.getPrecioUnitario(),
                            subtotal
                    );
                }

                writer.println("----------------------------------------");
                writer.printf("TOTAL: $%.2f%n", pedido.getTotal());
                writer.println("========================================");
                writer.println("       Gracias por su compra!           ");
                writer.println("========================================");
            }

            return nombreArchivo;

        } catch (IOException e) {
            // Si hay error de escritura, lo propagamos como excepción de runtime
            throw new RuntimeException("Error al generar el archivo de factura: " + e.getMessage(), e);
        }
    }
}
