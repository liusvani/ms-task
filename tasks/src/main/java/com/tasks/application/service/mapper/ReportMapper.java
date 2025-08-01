package com.tasks.application.service.mapper;


import com.tasks.domain.model.Report;
import com.tasks.domain.model.ReportStatus;
import com.tasks.web.ReportRequest;
import com.tasks.web.ReportResponse;

public class ReportMapper {
    public static Report toEntity(ReportRequest request) {
        Report report = new Report();
        report.setJobId(request.getJobId());
        report.setIdTransaccion(request.getIdTransaccion());
        report.setProduct(request.getProduct());
        report.setCategory(request.getCategory());
        report.setCantidadVendida(request.getCantidadVendida());
        report.setPrecioUnitario(request.getPrecioUnitario());
        report.setDescuento(request.getDescuento());
        report.setTotal(request.getTotal());
        report.setCliente(request.getCliente());
        report.setFormaPago(request.getFormaPago());
        report.setVendedor(request.getVendedor());
        report.setUbicacion(request.getUbicacion());
        report.setStatus(ReportStatus.SUCCESS);
        return report;
    }

    public static ReportResponse toResponse(Report report) {
        return new ReportResponse(
                report.getJobId(),
                report.getIdTransaccion(),
                report.getProduct(),
                report.getCategory(),
                report.getCantidadVendida(),
                report.getPrecioUnitario(),
                report.getDescuento(),
                report.getTotal(),
                report.getCliente(),
                report.getFormaPago(),
                report.getVendedor(),
                report.getUbicacion(),
                report.getStatus()
        );
    }
}
