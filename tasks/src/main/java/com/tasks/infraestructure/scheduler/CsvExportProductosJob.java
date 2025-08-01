package com.tasks.infraestructure.scheduler;

import com.tasks.application.service.ExportProductosMasVendidosService;
import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.domain.model.ProductosMasVendidos;
import com.tasks.infraestructure.csv.CsvWriterProductosMasVendidosAdapter;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DisallowConcurrentExecution // evita que el job se ejecute en paralelo
public class CsvExportProductosJob implements Job {

    private final ExportProductosMasVendidosService exportProductosMasVendidosService;
    private final CsvWriterProductosMasVendidosAdapter csvWriterProductosMasVendidosAdapter;

    public CsvExportProductosJob(ExportProductosMasVendidosService exportProductosMasVendidosService, CsvWriterProductosMasVendidosAdapter csvWriterProductosMasVendidosAdapter) {
        this.exportProductosMasVendidosService = exportProductosMasVendidosService;
        this.csvWriterProductosMasVendidosAdapter = csvWriterProductosMasVendidosAdapter;
    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            List<ProductosMasVendidos> productos = exportProductosMasVendidosService.execute();
            csvWriterProductosMasVendidosAdapter.write(productos);
            System.out.println("Se está ejecutando el csvProductJob: " +productos);
        } catch (Exception e) {
            throw new JobExecutionException("Error al generar el CSV automáticamente", e);
        }
    }

    public List<ProductosMasVendidos> fetchClientesPorUbicacion() {
        return List.of(); // o datos simulados
    }

}
