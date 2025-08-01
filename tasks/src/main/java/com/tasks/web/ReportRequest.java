package com.tasks.web;

import com.tasks.domain.model.ReportStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReportRequest {

    @NotBlank(message = "El job ID no puede estar vacío.")
    private String jobId;

    @NotBlank(message = "El ID de transacción es obligatorio.")
    private String idTransaccion;

    @NotBlank(message = "El producto es obligatorio.")
    private String product;

    @NotBlank(message = "La categoría es obligatoria.")
    private String category;

    @Min(value = 1, message = "Debe haber al menos una unidad vendida.")
    private int cantidadVendida;

    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a cero.")
    private double precioUnitario;

    @DecimalMin(value = "0.0", message = "El descuento no puede ser negativo.")
    private double descuento;

    @DecimalMin(value = "0.0", message = "El total no puede ser negativo.")
    private double total;

    @NotBlank(message = "El cliente es obligatorio.")
    private String cliente;

    @NotBlank(message = "La forma de pago es obligatoria.")
    private String formaPago;

    @NotBlank(message = "El vendedor es obligatorio.")
    private String vendedor;

    @NotBlank(message = "La ubicación es obligatoria.")
    private String ubicacion;

    @NotNull(message = "El estado del reporte no puede ser nulo")
    private ReportStatus status;


}

