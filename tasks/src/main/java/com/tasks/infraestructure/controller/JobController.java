package com.tasks.infraestructure.controller;

import com.tasks.infraestructure.repository.QuartzSchedulerAdapter;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final Scheduler scheduler;
    private final QuartzSchedulerAdapter quartzSchedulerAdapter;

    /*Inicia un job pasandole por parametro el nombre del job y luego se programa
    * un trigger para que ejecute el job automaticamente cada una hora. Si existe algun error dispara
    *  */
    @PostMapping("/start/{jobId}")
    public ResponseEntity<String> startJob(@PathVariable String jobId) {
        try {
            JobKey jobKey = new JobKey(jobId);
            if (!scheduler.checkExists(jobKey)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El Job '" + jobId + "' al que hace referencia no existe.");
            }
            scheduler.triggerJob(jobKey);
            return ResponseEntity.ok("Job '" + jobId + "' ejecutado correctamente.");
        } catch (SchedulerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al ejecutar Job: " + e.getMessage());
        }
    }


    @PostMapping("/pause/{jobId}")
    public ResponseEntity<String> pauseJob(@PathVariable String jobId) {
        try {
            JobKey jobKey = new JobKey(jobId);
            scheduler.pauseJob(jobKey);
            return ResponseEntity.ok("Job " + jobId + " pausado correctamente.");
        } catch (SchedulerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al pausar Job: " + e.getMessage());
        }
    }

    @PostMapping("/resume/{jobId}")
    public ResponseEntity<String> resumeJob(@PathVariable String jobId) {
        try {
            JobKey jobKey = new JobKey(jobId);
            if (!scheduler.checkExists(jobKey)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El Job '" + jobId + "' no existe.");
            }
            scheduler.resumeJob(jobKey);
            return ResponseEntity.ok("Job '" + jobId + "' reanudado correctamente.");
        } catch (SchedulerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al reanudar Job: " + e.getMessage());
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<String> deleteJob() {
        try {
            scheduler.deleteJob(new JobKey("productosJob"));
            return ResponseEntity.ok("Job eliminado");
        } catch (SchedulerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar job");
        }
    }
}

