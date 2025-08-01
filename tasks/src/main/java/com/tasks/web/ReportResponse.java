package com.tasks.web;

import com.tasks.domain.model.ReportStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
public class ReportResponse {
    private String jobId;
    private String idTransaccion;
    private String product;
    private String category;
    private int cantidadVendida;
    private double precioUnitario;
    private double descuento;
    private double total;
    private String cliente;
    private String formaPago;
    private String vendedor;
    private String ubicacion;
    private ReportStatus status;
}

