package com.tasks.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes_por_ubicacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientesPorUbicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ubicacion;
    private Long cantidad;
}

