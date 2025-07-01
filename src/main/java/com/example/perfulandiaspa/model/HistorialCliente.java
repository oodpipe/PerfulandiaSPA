package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class HistorialCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", unique = true, nullable = false)
    private Cliente cliente;

    @ElementCollection
    @CollectionTable(name = "historial_visitas",
            joinColumns = @JoinColumn(name = "historial_id"))
    @Column(name = "visita")
    private List<String> visitas = new ArrayList<>();

    @Lob
    private String alergias;

    @Lob
    private String condicionesMedicas;

    private String preferencias;

    @Column(updatable = false)
    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaUltimaActualizacion;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
}