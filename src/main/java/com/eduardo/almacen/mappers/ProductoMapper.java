package com.eduardo.almacen.mappers;

import com.eduardo.almacen.dto.productos.ProductoRequest;
import com.eduardo.almacen.dto.productos.ProductoResponse;
import com.eduardo.almacen.entities.Producto;
import com.eduardo.almacen.enums.Categoria;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public  Producto requestAEntidad(ProductoRequest productoRequest, Categoria categoria) {
        if(productoRequest == null) return null;

        return Producto.builder().
                nombre(productoRequest.nombre())
                .Categoria(categoria)
                .precio(productoRequest.precio())
                .cantidad(productoRequest.cantidad()).build();
    }

    public  ProductoResponse entidadAResponse(Producto producto) {
        if(producto == null) return null;
        return  new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria().getDescripcion(),
                producto.getPrecio(),
                producto.getCantidad()
        );
    }
}
