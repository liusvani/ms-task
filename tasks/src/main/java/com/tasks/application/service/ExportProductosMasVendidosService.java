package com.tasks.application.service;


import com.tasks.domain.model.ProductosMasVendidos;
import com.tasks.domain.ports.ExportProductosMasVendidos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportProductosMasVendidosService {
    private final ExportProductosMasVendidos exportProductosMasVendidos;

    public ExportProductosMasVendidosService(ExportProductosMasVendidos exportProductosMasVendidos) {
        this.exportProductosMasVendidos = exportProductosMasVendidos;
    }


    public List<ProductosMasVendidos> execute() {
        return exportProductosMasVendidos.fetchProductosMasVendidos();
    }

}
