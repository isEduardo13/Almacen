package com.eduardo.almacen.enums;

import com.eduardo.almacen.exceptions.RecursoNoEncontradoException;
import com.eduardo.almacen.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Categoria {
    ALIMENTOS("Alimento"),
    HIGIENE("Higiene"),
    JUGUETE("Juguete") ,
    ELECTRONICA("Electrónica"),
    ROPA("Ropa"),
    ACCESORIOS("Accesorios"),
    FARMACIA("Farmacia"),;

    private final String descripcion;

    public static Categoria obtenerCategoriaPorDescripcion(String descripcion){
        StringCustomUtils.validarNoVacio(descripcion,"La descripcion no puede ser vacia");
        String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion);
        for (Categoria categoria : Categoria.values()) {
         if (StringCustomUtils.quitarAcentos(categoria.descripcion).equalsIgnoreCase(descripcionNormalizada)) {
         return categoria;}
        }
        throw  new RecursoNoEncontradoException("No existe una categoria con la descripcion "+ descripcion);
    }

}

