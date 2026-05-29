package com.example.inventario.backend.config;

import com.example.inventario.backend.model.Producto;
import com.example.inventario.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica si la tabla de productos está vacía

        System.out.println("Sembrando base de datos con productos iniciales...");

        List<Producto> productosIniciales = Arrays.asList(
                new Producto(null, "Paracetamol 500mg", new BigDecimal("12.50"), 85),
                new Producto(null, "Ibuprofeno 400mg", new BigDecimal("15.30"), 92),
                new Producto(null, "Amoxicilina 500mg", new BigDecimal("22.10"), 64),
                new Producto(null, "Omeprazol 20mg", new BigDecimal("18.45"), 77),
                new Producto(null, "Losartán Potásico 50mg", new BigDecimal("25.00"), 50),
                new Producto(null, "Atorvastatina 20mg", new BigDecimal("34.20"), 88),
                new Producto(null, "Metformina 850mg", new BigDecimal("19.90"), 95),
                new Producto(null, "Cetirizina 10mg", new BigDecimal("11.15"), 60),
                new Producto(null, "Loratadina 10mg", new BigDecimal("13.80"), 73),
                new Producto(null, "Azitromicina 500mg", new BigDecimal("16.50"), 55),
                new Producto(null, "Diclofenac Sódico 75mg", new BigDecimal("14.25"), 81),
                new Producto(null, "Ketorolaco 10mg", new BigDecimal("17.35"), 69),
                new Producto(null, "Clonazepam 2mg", new BigDecimal("28.90"), 52),
                new Producto(null, "Alprazolam 0.75mg/ml", new BigDecimal("31.40"), 66),
                new Producto(null, "Enalapril 10mg", new BigDecimal("16.80"), 90),
                new Producto(null, "Ciprofloxacino 500mg", new BigDecimal("24.50"), 58),
                new Producto(null, "Fluconazol 150mg", new BigDecimal("10.99"), 83),
                new Producto(null, "Pantoprazol 40mg", new BigDecimal("42.15"), 71),
                new Producto(null, "Ranitidina 150mg", new BigDecimal("12.00"), 100),
                new Producto(null, "Buscapina Compositum", new BigDecimal("21.60"), 74),
                new Producto(null, "Tramadol 50mg", new BigDecimal("29.75"), 63),
                new Producto(null, "Meloxicam 15mg", new BigDecimal("23.40"), 79),
                new Producto(null, "Prednisona 5mg", new BigDecimal("11.50"), 87),
                new Producto(null, "Dexametasona 4mg/2ml", new BigDecimal("35.80"), 54),
                new Producto(null, "Complejo B Vitaminado", new BigDecimal("18.90"), 91),
                new Producto(null, "Vitamina C 1g", new BigDecimal("14.10"), 98),
                new Producto(null, "Ácido Fólico 5mg", new BigDecimal("15.25"), 82),
                new Producto(null, "Sulfato Ferroso 200mg", new BigDecimal("13.50"), 76),
                new Producto(null, "Salbutamol 100mcg", new BigDecimal("27.30"), 61),
                new Producto(null, "Budesonida 200mcg", new BigDecimal("48.90"), 53)
        );

        productoRepository.saveAll(productosIniciales);
        System.out.println("Sembrado de productos completado exitosamente.");

    }
}
