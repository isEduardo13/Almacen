package com.eduardo.almacen.services;


import com.eduardo.almacen.dto.productos.ProductoRequest;
import com.eduardo.almacen.dto.productos.ProductoResponse;
import com.eduardo.almacen.entities.Producto;
import com.eduardo.almacen.enums.Categoria;
import com.eduardo.almacen.exceptions.RecursoNoEncontradoException;
import com.eduardo.almacen.mappers.ProductoMapper;
import com.eduardo.almacen.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> listar(String nombre, String  categoria, BigDecimal preciomin, BigDecimal preciomax) {
        log.info("Listando todos los productos");

        return productoRepository.findAll().stream().map(productoMapper :: entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponse obtenerPorId(long id) {
        return productoMapper.entidadAResponse(obtenerProductoOException(id));
    }

    @Override
    public ProductoResponse registrar(ProductoRequest productoRequest) {
        log.info("Registrando producto");
        Categoria categoria =  Categoria.obtenerCategoriaPorDescripcion(productoRequest.categoria());
        Producto producto =  productoMapper.requestAEntidad(productoRequest , Categoria.obtenerCategoriaPorDescripcion(productoRequest.categoria()));
        productoRepository.save(producto);
        return productoMapper.entidadAResponse(producto);
    }

    @Override
    public ProductoResponse actualizar(ProductoRequest productoRequest, long id) {
        Producto producto = obtenerProductoOException(id);
        log.info("Actualizando producto con el id: {}", id);

        producto.actualizar(
                productoRequest.nombre(),
                Categoria.obtenerCategoriaPorDescripcion(productoRequest.categoria().trim()),
                productoRequest.precio(),
                productoRequest.cantidad()
        );
        log.info("Producto {} actualizado", producto.getNombre());

        return productoMapper.entidadAResponse(producto);
    }

    @Override
    public void eliminar(Long id) {
        Producto proeducto = obtenerProductoOException(id);
        log.info("Eliminando producto");

        productoRepository.delete(proeducto);

        log.info("eliminando producto");


    }
    private Producto obtenerProductoOException(Long id ){
        log.info("Obteniendo del producto {}", id);
        return productoRepository.findById(id).orElseThrow(() ->new RecursoNoEncontradoException("Producto no encontrado con id: " + id ));
    }
}
