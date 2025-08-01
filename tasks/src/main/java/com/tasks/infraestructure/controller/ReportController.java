package com.tasks.infraestructure.controller;

import com.tasks.application.service.ReportService;
import com.tasks.application.service.mapper.ReportMapper;
import com.tasks.domain.job.TaskReportJob;
import com.tasks.web.ReportRequest;
import com.tasks.web.ReportResponse;
import org.quartz.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService service;
    private  final Scheduler scheduler;

    public ReportController(ReportService service, Scheduler scheduler) {
        this.service = service;
        this.scheduler = scheduler;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReportRequest request) {
        try {
            service.createReport(ReportMapper.toEntity(request));
            return ResponseEntity.ok(Map.of("mensaje", "Reporte creado"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/iniciar")
    public ResponseEntity<String> ejecutarJob() {
        try {
            JobDetail job = JobBuilder.newJob(TaskReportJob.class)
                    .withIdentity("taskMinuteJob", "grupo1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("triggerEveryMinute", "grupo1")
                    .startNow()
                    .build();

            scheduler.scheduleJob(job, trigger);
            return ResponseEntity.ok("Job lanzado correctamente.");
        } catch (SchedulerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al lanzar el Job.");
        }
    }

    @GetMapping
    public ResponseEntity<List<ReportResponse>> findAll() {
        return ResponseEntity.ok(
                service.getAllReports().stream()
                        .map(ReportMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(ReportMapper.toResponse(service.getReportById(id)));
    }
     /*
    @GetMapping("/product_sales")
    public ResponseEntity<List<String>> getProductsOrderedBySales() {
        try {
            List<String> productos = service.getProductsOrderedBySales();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(List.of("Error al obtener el producto más vendido: " + e.getMessage()));
        }
    }

    @GetMapping("/ubication_clients")
    public ResponseEntity<List<String>> getUbicationByClientes() {
        try {
            List<String> ubication = Collections.singletonList(service.getUbicationByClient());
            return ResponseEntity.ok(ubication);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(List.of("Error al obtener el producto más vendido: " + e.getMessage()));
        }
    }*/




}
