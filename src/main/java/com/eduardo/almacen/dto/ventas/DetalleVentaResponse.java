package com.eduardo.almacen.dto.ventas;

import java.math.BigDecimal;

public record DetalleVentaResponse(
        Long idProducto,
        String nombreProducto,
        Integer catidadProdcuto,
        BigDecimal precioProducto,
        BigDecimal subtotal
) {
}
