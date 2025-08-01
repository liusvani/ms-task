package com.tasks.infraestructure.scheduler;

import com.tasks.application.service.ExportClientesPorUbicacionService;
import com.tasks.domain.model.ClientesPorUbicacion;
import com.tasks.infraestructure.csv.CsvWriterClientPorUbicacionAdapter;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@DisallowConcurrentExecution // evita que el job se ejecute en paralelo
public class CsvExportClientesJob implements Job {

    private final ExportClientesPorUbicacionService exportClientesPorUbicacionService;
    private final CsvWriterClientPorUbicacionAdapter csvWriterAdapter;

    public CsvExportClientesJob(ExportClientesPorUbicacionService exportClientesPorUbicacionService, CsvWriterClientPorUbicacionAdapter csvWriterAdapter) {
        this.exportClientesPorUbicacionService = exportClientesPorUbicacionService;
        this.csvWriterAdapter = csvWriterAdapter;
    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            List<ClientesPorUbicacion> clientes = exportClientesPorUbicacionService.execute();
            csvWriterAdapter.write(clientes);
            System.out.println("Se está ejecutando el csvClientJob: " +clientes);
        } catch (Exception e) {
            throw new JobExecutionException("Error al generar el CSV automáticamente", e);
        }
    }

    public List<ClientesPorUbicacion> fetchClientesPorUbicacion() {
        return List.of(); // o datos simulados
    }

}
