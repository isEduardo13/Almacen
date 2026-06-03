package com.eduardo.almacen.dto.ventas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetalleVentaRequest(
        @NotNull(message = "El ID de producto es requerido")
        @Positive(message = "El ID de producto debe de ser pisitivo")
        Long idProducto,

        @NotNull(message = "La cantidad es requerida")
        @Positive(message = "La cantidad del producto debe ser positiva ")
        Integer CantidadProducto
) {
}
