package com.eduardo.almacen.controllers;

import com.eduardo.almacen.dto.ventas.VentaRequest;
import com.eduardo.almacen.dto.ventas.VentaResponse;
import com.eduardo.almacen.services.VentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ventas")
@AllArgsConstructor
@Validated
@Slf4j
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaResponse>> listar() {
        return ResponseEntity.ok(ventaService.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }
    @PostMapping
    public ResponseEntity<VentaResponse> registrar(@RequestBody @Valid VentaRequest ventaRequest) {
        return ResponseEntity.ok(ventaService.registrar(ventaRequest));
    }
}
