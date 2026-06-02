package com.eduardo.almacen.controllers;

import com.eduardo.almacen.dto.sucursales.SucursalRequest;
import com.eduardo.almacen.dto.sucursales.SucursalResponse;
import com.eduardo.almacen.services.SucursalService;
import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sucursales")
@AllArgsConstructor
@Validated
public class SucursalController {

    private final SucursalService sucursalService;


    @GetMapping
    public ResponseEntity<List<SucursalResponse>> listar(){
        return ResponseEntity.ok(sucursalService.listar());

    }
    @GetMapping("/{id}")
    public ResponseEntity<SucursalResponse> ObtenerPorId(@PathVariable @Positive(message = "El id debe ser positivo") Long id){
        return ResponseEntity.ok(sucursalService.ObtenerPorId(id));

    }

    @PostMapping
    public ResponseEntity<SucursalResponse> registrar(@Valid @RequestBody SucursalRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.registrar(request));
    }







}
