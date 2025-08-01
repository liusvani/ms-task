package com.tasks.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "report_jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)

    private UUID id;

    @Column(name = "job_id", nullable = false)
    private String jobId;

    @Column(name = "id_transaccion", nullable = false)
    private String idTransaccion;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "cantidad_vendida", nullable = false)
    private int cantidadVendida;

    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @Column(name = "descuento", nullable = false)
    private double descuento;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "cliente", nullable = false)
    private String cliente;

    @Column(name = "forma_pago", nullable = false)
    private String formaPago;

    @Column(name = "vendedor", nullable = false)
    private String vendedor;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "status", nullable = false)
    private ReportStatus status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
