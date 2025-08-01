package com.tasks.domain.ports;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.model.ProductosMasVendidos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface ExportClientesPorUbicacion  {
    void export(List<ClientesPorUbicacion> clientes);
    List<ClientesPorUbicacion> fetchClientesPorUbicacion();

}
