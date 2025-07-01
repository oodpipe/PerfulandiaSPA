package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    private String direccion;
    private String telefono;

    @Column(updatable = false)
    private LocalDate fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @OneToOne(mappedBy = "cliente",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private HistorialCliente historial;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Pago> pagos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDate.now();
    }

    // Método helper para relación bidireccional
    public void agregarHistorial(HistorialCliente historial) {
        historial.setCliente(this);
        this.historial = historial;
    }
}