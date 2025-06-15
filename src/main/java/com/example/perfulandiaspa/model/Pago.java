package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fecha;

    private String estado; // por pagar, pagado

    private double total;

    // Relación con Usuario (muchos pagos pueden ser de un usuario)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Relación con Pedido (un pago corresponde a un pedido)
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    // Productos involucrados en el pago (mismo pedido)
    @ManyToMany
    @JoinTable(
            name = "pago_producto",
            joinColumns = @JoinColumn(name = "pago_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;
}
