package com.eduardo.almacen.dto;

public record CustomErrorResponse(
        int codigo,
        String mensaje
) {
}
