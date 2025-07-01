package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    private LocalDate fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @OneToOne(mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private HistorialCliente historialPerfumeria;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDate.now();
    }
}