package com.eduardo.almacen.services;

import com.eduardo.almacen.dto.productos.ProductoRequest;
import com.eduardo.almacen.dto.productos.ProductoResponse;
import com.eduardo.almacen.entities.Producto;
import com.eduardo.almacen.enums.Categoria;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {

    List<ProductoResponse> listar(String nombre, String  categoria, BigDecimal preciomin, BigDecimal preciomax);

    ProductoResponse obtenerPorId(long id);

    ProductoResponse registrar(ProductoRequest productoRequest);

    ProductoResponse actualizar(ProductoRequest productoRequest, long id);

    void  eliminar(Long id);

}
