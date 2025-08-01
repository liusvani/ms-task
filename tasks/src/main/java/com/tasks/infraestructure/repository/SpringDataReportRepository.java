package com.tasks.infraestructure.repository;

import com.tasks.domain.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataReportRepository extends JpaRepository<Report, UUID> {
    boolean existsByProduct(String product);
    boolean existsByJobId(String jobId);
    boolean existsByIdTransaccion(String idTransaccion);
}
