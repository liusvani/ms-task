package com.tasks.infraestructure.scheduler;

import com.tasks.application.service.ReportService;
import org.quartz.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@DisallowConcurrentExecution
public class ClientesPorUbicacionJob implements Job {


    private final ReportService reportService;
    private final Scheduler scheduler;

    public ClientesPorUbicacionJob(ReportService reportService, Scheduler scheduler) {
        this.reportService = reportService;
        this.scheduler = scheduler;
    }


    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<String> clientes = Collections.singletonList(reportService.getUbicationByClient());
        System.out.println("Se está ejecutando el ubicacionJob. "+clientes+ " filas afectadas");
    }

    public void getJobsRunning(){
        try {
            List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
            List<String> jobNames = currentlyExecutingJobs.stream()
                    .map(ctx -> ctx.getJobDetail().getKey().getName())
                    .collect(Collectors.toList());
            System.out.println("getCurrentlyExecutingJobs." + jobNames);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}

