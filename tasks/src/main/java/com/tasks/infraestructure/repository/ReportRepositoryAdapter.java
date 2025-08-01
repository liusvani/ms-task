package com.tasks.infraestructure.repository;

import com.tasks.domain.model.Report;
import com.tasks.domain.ports.ReportRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ReportRepositoryAdapter implements ReportRepository {
    private final SpringDataReportRepository jpaRepository;

    public ReportRepositoryAdapter(SpringDataReportRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Report save(Report report) {
        return jpaRepository.save(report);
    }

    @Override
    public Optional<Report> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Report> findAll() {
        return jpaRepository.findAll();
    }


    @Override
    public boolean existsByJobId(String jobId) {
        return jpaRepository.existsByJobId(jobId);
    }

    @Override
    public boolean existsByIdTransaccion(String idTransaccion) {
        return jpaRepository.existsByIdTransaccion(idTransaccion);
    }

}
