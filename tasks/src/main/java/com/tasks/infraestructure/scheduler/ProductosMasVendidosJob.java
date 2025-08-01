package com.tasks.infraestructure.scheduler;

import com.tasks.application.service.ReportService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DisallowConcurrentExecution
public class ProductosMasVendidosJob implements Job {


    private final ReportService reportService;

    public ProductosMasVendidosJob(ReportService reportService) {
        this.reportService = reportService;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<String> productos = reportService.getProductsOrderedBySales();
        System.out.println("Se está ejecutando productJob " + productos.size()+ " filas afectadas");

    }

}

