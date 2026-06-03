package com.eduardo.almacen.enums;

import com.eduardo.almacen.exceptions.RecursoNoEncontradoException;
import com.eduardo.almacen.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum EstadoVenta {
    REGISTRADA("Registrada", 1L),
    CANCELADA("Cancelada", 0L);

    private final String description;

    private final Long codigo;

    public static EstadoVenta obtenerEstadoVentaPorDescripcion(String descripcion){
        StringCustomUtils.validarNoVacio(descripcion,"La descripcion no puede ser vacia");
        String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion);
        for (EstadoVenta estadoVenta : EstadoVenta.values()) {
            if (StringCustomUtils.quitarAcentos(estadoVenta.description).equalsIgnoreCase(descripcionNormalizada)) {
                return estadoVenta;}
        }
        throw  new RecursoNoEncontradoException("No existe un estado de venta con la descripcion: "+ descripcion);
    }

    public static EstadoVenta obtenerEstadoVentaPorCodigo(Long codigo){
        for (EstadoVenta estadoVenta : EstadoVenta.values()) {
            if(Objects.equals(codigo, estadoVenta.codigo)){
                return estadoVenta;
            }
        }
        throw new RecursoNoEncontradoException("No existe un estado de venta con el codigo: "+ codigo);
    }

}
