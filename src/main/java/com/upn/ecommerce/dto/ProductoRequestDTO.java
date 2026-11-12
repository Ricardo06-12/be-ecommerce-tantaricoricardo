package com.upn.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductoRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La categoria es obligatoria")
    private String categoria;

    @NotNull(message = "El precio de compra es obligatorio")
    @DecimalMin(value = "0.01")
    private BigDecimal precioCompra;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.01")
    private BigDecimal precioVenta;

    @NotNull(message = "El stock es obligatorio")
    @Min(0)
    private Integer stock;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El codigo de barras es obligatorio")
    private String codigoBarras;

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;
}
