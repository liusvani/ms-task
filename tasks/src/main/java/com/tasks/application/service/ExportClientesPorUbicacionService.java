package com.tasks.application.service;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.ports.ExportClientesPorUbicacion;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExportClientesPorUbicacionService {
    private final ExportClientesPorUbicacion exportClientesPorUbicacion;

    public ExportClientesPorUbicacionService(ExportClientesPorUbicacion exportClientesPorUbicacion) {
        this.exportClientesPorUbicacion = exportClientesPorUbicacion;
    }


    public List<ClientesPorUbicacion> execute() {
        return exportClientesPorUbicacion.fetchClientesPorUbicacion();
    }

}
