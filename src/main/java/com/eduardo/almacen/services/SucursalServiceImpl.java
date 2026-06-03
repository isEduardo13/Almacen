package com.eduardo.almacen.services;

import com.eduardo.almacen.dto.sucursales.SucursalRequest;
import com.eduardo.almacen.entities.Sucursal;
import com.eduardo.almacen.dto.sucursales.SucursalResponse;
import com.eduardo.almacen.exceptions.RecursoNoEncontradoException;
import com.eduardo.almacen.mappers.SucursalMapper;
import com.eduardo.almacen.repositories.SucursalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j // Agrega un logger para registrar información de depuración
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    private final SucursalMapper sucursalMapper;

    @Override
    @Transactional(readOnly = true) // Marca este
    public List<SucursalResponse> listar() {
        log.info("Lista de sucursales");
        return sucursalRepository .findAll()
                .stream()
                .map(sucursalMapper::entidadAResponse)
                .toList();
    }

    @Override
    public SucursalResponse obtenerPorId(Long id) {
        return sucursalMapper.entidadAResponse(obtenerSucursalException(id));
    }


    @Override
    public SucursalResponse registrar(SucursalRequest request) {
        log.info("Registrando Sucursal...");
        validarDatosUnicos(request);
        Sucursal sucursal = sucursalMapper.requestAEntidad(request);
        sucursalRepository.save(sucursal);
        return sucursalMapper.entidadAResponse(sucursal);
    }

    @Override
    public SucursalResponse actualizar(SucursalRequest request, Long id) {
        Sucursal sucursal = obtenerSucursalException(id);
        log.info("Actualizando Sucursal con el id: {}", id);

        validarCambiosUnicos(request, id);

        sucursal.actualizar(request.nombre(), request.direccion());
        log.info("Sucursal con el id: {} actualizado correctamente", id);

        return sucursalMapper.entidadAResponse(sucursal);
    }



    @Override
    public void eliminar(Long id) {
        Sucursal sucursal = obtenerSucursalException(id);
        log.info("Eliminando Sucursal con el id: {}", id);
        sucursalRepository.delete(sucursal);
        log.info("Sucursal con el id: {} eliminado correctamente", id);

    }
    private Sucursal obtenerSucursalException(Long id) {
        log.info("Buscando sucursal con ID: {}", id);
        return sucursalRepository.findById(id)
                .orElseThrow((() ->
                        new RecursoNoEncontradoException("Sucursal no encontrada con id: " + id)));
    }

    private void validarDatosUnicos (SucursalRequest request) {
        log.info("Validando sucursal request...");
        if(sucursalRepository.existsByNombreIgnoreCase(request.nombre().trim()))
            throw new IllegalArgumentException("Ya existe una sucursal con el nombre de:" + request.nombre());
    }
    private void validarCambiosUnicos(SucursalRequest request, Long id) {
        log.info("Validando cambios únicos para sucursal con ID: {}", id);
        if(sucursalRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id))
            throw new IllegalArgumentException("Ya existe una sucursal con el nombre de:" + request.nombre());
    }
}