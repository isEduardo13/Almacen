package com.eduardo.almacen.entities;

import com.eduardo.almacen.enums.EstadoVenta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "VENTAS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA")
    private Long id;

    @Column(name ="ESTADO", nullable =  false)
    @Enumerated(EnumType.STRING)
    private EstadoVenta estadoVenta;

    @Column(name = "FECHA" , nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUCURSAL" , nullable = false)
    private Sucursal sucursal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentas;

    public void agregarDetalle(DetalleVenta detalleVenta){
        if(detalleVenta == null){
            throw new IllegalArgumentException("El detalle de venta no puede ser nula");
        }
        this.detalleVentas.add(detalleVenta);
        detalleVenta.setVenta(this);//Notifica a venta de que se agrego un detalle venta
    }


    public void cancelar(){
        if (this.estadoVenta == EstadoVenta.CANCELADA){
            throw new IllegalStateException("La venta ya esta cancelada");
        }

        this.estadoVenta = EstadoVenta.CANCELADA;
    }



}
