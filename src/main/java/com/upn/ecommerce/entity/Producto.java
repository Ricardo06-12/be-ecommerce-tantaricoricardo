package com.upn.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
@NamedQueries({
    @NamedQuery(
        name = "Producto.buscarPorCategoriaNamed",
        query = "SELECT p FROM Producto p WHERE LOWER(p.categoria) = LOWER(:categoria)"
    ),
    @NamedQuery(
        name = "Producto.buscarConStockMinimoNamed",
        query = "SELECT p FROM Producto p WHERE p.stock >= :stockMinimo ORDER BY p.precioVenta ASC"
    )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(name = "precio_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(name = "codigo_barras", nullable = false, unique = true, length = 50)
    private String codigoBarras;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;
}
