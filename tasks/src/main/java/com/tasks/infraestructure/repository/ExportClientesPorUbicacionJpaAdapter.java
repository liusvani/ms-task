package com.tasks.infraestructure.repository;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.ports.ClientesPorUbicacionRepository;
import com.tasks.domain.ports.ExportClientesPorUbicacion;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Primary
@Repository
public class ExportClientesPorUbicacionJpaAdapter implements ExportClientesPorUbicacion {

    private final ClientesPorUbicacionRepository repository;

    public ExportClientesPorUbicacionJpaAdapter(ClientesPorUbicacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void export(List<ClientesPorUbicacion> clientes) {

    }

    @Override
    public List<ClientesPorUbicacion> fetchClientesPorUbicacion() {
        return repository.findAll();
    }

}
