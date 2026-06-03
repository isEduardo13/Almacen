package com.eduardo.almacen.mappers;

import com.eduardo.almacen.dto.sucursales.SucursalResponse;
import com.eduardo.almacen.dto.ventas.DetalleVentaRequest;
import com.eduardo.almacen.dto.ventas.DetalleVentaResponse;
import com.eduardo.almacen.dto.ventas.VentaRequest;
import com.eduardo.almacen.dto.ventas.VentaResponse;
import com.eduardo.almacen.entities.DetalleVenta;
import com.eduardo.almacen.entities.Producto;
import com.eduardo.almacen.entities.Sucursal;
import com.eduardo.almacen.entities.Venta;
import com.eduardo.almacen.enums.EstadoVenta;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VentaMapper {

    private final SucursalMapper sucursalMapper;

    public Venta requestAEntidad(VentaRequest ventaRequest, EstadoVenta estado) {
        if (ventaRequest == null) {
            return null;
        }
        List<DetalleVenta> detalles = ventaRequest.productos().stream()
                .map(dto -> DetalleVenta.builder()
                        .cantidadProducto(dto.CantidadProducto())
                        .producto(Producto.builder().id(dto.idProducto()).build())
                        .build()).toList();

        return Venta.builder()
                .estadoVenta(estado)
                .sucursal(Sucursal.builder().id(ventaRequest.idSucursal()).build())
                .detalleVentas(detalles)
                .build();


    }

    public VentaResponse entidadAResponse(Venta entidadVenta) {
        if (entidadVenta == null) return null;
        List<DetalleVentaResponse> detallesResponse = entidadVenta.getDetalleVentas().stream()
                .map(detalle -> new DetalleVentaResponse(
                        detalle.getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidadProducto(),
                        detalle.getPrecioProducto(),
                        detalle.calcularSubtotal()
                ))
                .toList();
        BigDecimal totalGeneral = detallesResponse.stream()
                .map(DetalleVentaResponse::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new VentaResponse(
                entidadVenta.getId(),
                entidadVenta.getFecha().toString(),
                entidadVenta.getEstadoVenta().getDescription(),
                sucursalMapper.entidadAResponse(entidadVenta.getSucursal()),
                detallesResponse,
                totalGeneral
        );
    }
}

