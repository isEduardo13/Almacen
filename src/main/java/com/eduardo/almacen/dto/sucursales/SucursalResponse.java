package com.eduardo.almacen.dto.sucursales;

public record SucursalResponse(
        Long id,
        String nombre,
        String direccion
) {}