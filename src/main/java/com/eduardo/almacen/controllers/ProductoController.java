package com.eduardo.almacen.controllers;

import com.eduardo.almacen.dto.productos.ProductoRequest;
import com.eduardo.almacen.dto.productos.ProductoResponse;
import com.eduardo.almacen.dto.sucursales.SucursalResponse;
import com.eduardo.almacen.services.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/productos")
@AllArgsConstructor
@Validated
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listar(){
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable @Positive(message = "El id debe de ser positivo") long id){
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> registrar(@RequestBody @Valid ProductoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.registrar(request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoResponse> eliminar (@PathVariable @Positive(message = "El id debe ser positivo") long id){
        log.info("Eliminando producto {}", id);
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse>actualizar (@PathVariable @Positive long id, @RequestBody @Valid ProductoRequest request ){
        log.info("Actualizando el id del producto {}", id);
        ProductoResponse productoActualizado = productoService.actualizar(request, id);
        return ResponseEntity.ok(productoActualizado);

    }



}
