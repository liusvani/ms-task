package com.tasks.infraestructure.repository;

import com.tasks.domain.model.ClientesPorUbicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataClientePorUbicacionRepository extends JpaRepository<ClientesPorUbicacion, Long> {
    List<ClientesPorUbicacion> findAll();
}

