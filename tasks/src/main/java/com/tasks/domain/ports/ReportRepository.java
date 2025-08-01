package com.tasks.domain.ports;

import com.tasks.domain.model.Report;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ReportRepository  {
    Report save(Report report);
    Optional<Report> findById(UUID id);
    List<Report> findAll();

    boolean existsByJobId(String jobId);

    boolean existsByIdTransaccion(String idTransaccion);

}


