package com.eduardo.almacen.controllers;

import com.eduardo.almacen.dto.sucursales.SucursalRequest;
import com.eduardo.almacen.dto.sucursales.SucursalResponse;
import com.eduardo.almacen.services.SucursalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/sucursales")
@AllArgsConstructor
@Validated
@Slf4j
public class SucursalController {

    private final SucursalService sucursalService;


    @GetMapping
    public ResponseEntity<List<SucursalResponse>> listar(){
        return ResponseEntity.ok(sucursalService.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponse> obtenerPorId(@PathVariable @Positive(message = "El id debe ser positivo") long id){
        return ResponseEntity.ok(sucursalService.obtenerPorId(id));

    }

    @PostMapping
    public ResponseEntity<SucursalResponse> registrar(@Valid @RequestBody SucursalRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.registrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalResponse> actualizar(@PathVariable @Positive long id, @Valid @RequestBody SucursalRequest request){
        log.info("Actualizando el id de la Sucursal {}" , id);
        SucursalResponse sucursalActualizada = sucursalService.actualizar(request, id);
        return ResponseEntity.ok(sucursalActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable @Positive(message = "El id debe de ser positivo") long id){
    log.info("eliminando : {}"+ id);
    sucursalService.eliminar(id);
    return ResponseEntity.noContent().build();
    }









}
