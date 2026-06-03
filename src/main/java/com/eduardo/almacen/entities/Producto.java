package com.eduardo.almacen.entities;

import com.eduardo.almacen.enums.Categoria;
import com.eduardo.almacen.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "PRODUCTOS")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO")
    private Long id;

    @Column(name = "NOMBRE", length = 30, nullable = false)
    private String nombre;

    @Column(name = "CATEGORIA", nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria Categoria;

    @Column(name = "PRECIO", nullable = false)
    private BigDecimal precio;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    public void actualizar(String nombre, Categoria categoria, BigDecimal precio, Integer cantidad) {


        validarDatos(nombre, categoria, precio, cantidad);
        this.nombre = nombre;
        Categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public void aumentarCantidad(Integer cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("El cantidad debe de ser psotiva");
        }
        this.cantidad += cantidad;
    }

    public void descontarCantidad(Integer cantidad) {
        if (this.cantidad < cantidad) {
            throw new IllegalArgumentException("El cantidad insuficiente");
        }
        this.cantidad -= cantidad;

    }
    private void validarDatos(String nombre, Categoria categoria, BigDecimal precio, Integer cantidad){

        StringCustomUtils.validarTamanio(nombre, 5 ,30, "El nombre debe ser entre 5 y 30 caracteres");
        if(categoria==null){
            throw new IllegalArgumentException("El categoria no puede ser nulo");
        }
        if(precio==null || precio.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("El precio es requerido y debe de ser positivo");
        }
        if (cantidad==null || cantidad<0){
            throw new IllegalArgumentException("La cantidad es requerida y debe de ser positiva");
        }

    }
}
