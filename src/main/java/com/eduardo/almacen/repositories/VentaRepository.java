package com.eduardo.almacen.repositories;

import com.eduardo.almacen.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {

}
