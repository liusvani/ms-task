package com.tasks.domain.ports;

import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.infraestructure.csv.CsvWriterClientPorUbicacionAdapter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CsvExportClientesPorUbicacionAdapter implements ExportClientesPorUbicacion {

    private final CsvWriterClientPorUbicacionAdapter csvWriterAdapter;

    public CsvExportClientesPorUbicacionAdapter(CsvWriterClientPorUbicacionAdapter csvWriterAdapter) {
        this.csvWriterAdapter = csvWriterAdapter;
    }

    @Override
    public void export(List<ClientesPorUbicacion> clientes) {
        try {
            csvWriterAdapter.write(clientes);
        } catch (IOException e) {
            throw new RuntimeException("Error al exportar CSV", e);
        }
    }
    @Override
    public List<ClientesPorUbicacion> fetchClientesPorUbicacion() {
        return List.of(); //  lista vacía
    }

}

