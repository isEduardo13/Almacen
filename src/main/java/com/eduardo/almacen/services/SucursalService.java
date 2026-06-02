package com.eduardo.almacen.services;

import com.eduardo.almacen.dto.sucursales.SucursalRequest;
import com.eduardo.almacen.dto.sucursales.SucursalResponse;
import com.eduardo.almacen.entities.Sucursal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SucursalService {

    List<SucursalResponse> listar();

    SucursalResponse ObtenerPorId(Long id);

    SucursalResponse registrar(SucursalRequest request);

    SucursalResponse actualizar(SucursalRequest request, Long id);

    void eliminar(Long id);

}
