package com.eduardo.almacen.services;

import com.eduardo.almacen.dto.ventas.VentaRequest;
import com.eduardo.almacen.dto.ventas.VentaResponse;
import com.eduardo.almacen.entities.Venta;
import com.eduardo.almacen.enums.EstadoVenta;
import com.eduardo.almacen.exceptions.RecursoNoEncontradoException;
import com.eduardo.almacen.mappers.VentaMapper;
import com.eduardo.almacen.repositories.VentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    private final VentaMapper ventaMapper;

    @Transactional(readOnly = true)
    @Override
    public List<VentaResponse> listar() {
        log.info("Lista de sucursales");
        return ventaRepository.findAll().stream()
                .map(ventaMapper::entidadAResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public VentaResponse obtenerPorId(Long id) {
        return ventaMapper.entidadAResponse(obtenerVentaException(id));
    }

    @Override
    public VentaResponse registrar(VentaRequest request) {
      log.info("Registrando venta...");

      Venta venta = ventaMapper.requestAEntidad(request, EstadoVenta.REGISTRADA);
      ventaRepository.save(venta);
      return ventaMapper.entidadAResponse(venta);
    }

    @Override
    public void cancelar(Long id) {

    }

    private Venta obtenerVentaException(Long id) {
        log.info("Buscando sucursal con ID: {}", id);
        return ventaRepository.findById(id)
                .orElseThrow((() ->
                        new RecursoNoEncontradoException("Venta no encontrada con id: " + id)

                ));
    }
    ///Registrar venta asigna la fecha??





}
