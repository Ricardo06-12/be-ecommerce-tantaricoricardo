package com.upn.ecommerce.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String categoria;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private Integer stock;
    private String marca;
    private String codigoBarras;
    private LocalDate fechaIngreso;
}
