package com.eduardo.almacen.dto.productos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoRequest(
        @NotBlank()
        @Size(min = 5, max = 30, message = "El nombre debe tener entre 5 y 30 caracteres")
        String nombre,

        @NotBlank(message = "La categoria es requerida")
        String categoria,

        @NotNull(message = "El precio es requerido")
        @Positive(message = "El precio debe de ser positivo")
        BigDecimal precio,

        @NotNull(message = "La cantidad es requerida")
        @Positive(message = "La cantidad debe de ser positiva")
        Integer cantidad
) {
}
