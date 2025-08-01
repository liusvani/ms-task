package com.tasks.infraestructure.repository;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.model.ProductosMasVendidos;
import com.tasks.domain.ports.ExportProductosMasVendidos;
import com.tasks.domain.ports.VentasPorProductoRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class ExportProductosMasVendidosJpaAdapter implements ExportProductosMasVendidos {

    private final VentasPorProductoRepository repository;

    public ExportProductosMasVendidosJpaAdapter(VentasPorProductoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void export(List<ProductosMasVendidos> clientes) {

    }

    @Override
    public List<ProductosMasVendidos> fetchProductosMasVendidos() {
        return repository.findAll();
    }

}
