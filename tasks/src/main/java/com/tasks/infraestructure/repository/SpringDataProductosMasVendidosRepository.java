package com.tasks.infraestructure.repository;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.model.ProductosMasVendidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataProductosMasVendidosRepository extends JpaRepository<ProductosMasVendidos, Long> {
    List<ProductosMasVendidos> findAll();
}

