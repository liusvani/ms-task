package com.tasks.domain.ports;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.model.ProductosMasVendidos;
import com.tasks.infraestructure.csv.CsvWriterProductosMasVendidosAdapter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CsvExportProductosMasVendidosAdapter implements ExportProductosMasVendidos {

    private final CsvWriterProductosMasVendidosAdapter csvWriterProductosMasVendidosAdapter;

    public CsvExportProductosMasVendidosAdapter(CsvWriterProductosMasVendidosAdapter csvWriterProductosMasVendidosAdapter) {
        this.csvWriterProductosMasVendidosAdapter = csvWriterProductosMasVendidosAdapter;
    }


    @Override
    public void export(List<ProductosMasVendidos> productos) {
        try {
            csvWriterProductosMasVendidosAdapter.write(productos);
        } catch (IOException e) {
            throw new RuntimeException("Error al exportar CSV", e);
        }
    }
    @Override
    public List<ProductosMasVendidos> fetchProductosMasVendidos() {
        return List.of(); //  lista vacía
    }

}

