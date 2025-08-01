package com.tasks.domain.ports;

import com.tasks.domain.model.ClientesPorUbicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesPorUbicacionRepository extends JpaRepository<ClientesPorUbicacion, Long> {
    List<ClientesPorUbicacion> findAll();
}

