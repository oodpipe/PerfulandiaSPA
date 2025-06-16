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

    // Relación con Cliente (un pago pertenece a un cliente)
    @ManyToOne
    @JoinColumn(name = "cliente_id") // nombre de la columna en la tabla
    private Cliente cliente;

    // Relación con Pedido (un pago corresponde a un pedido)
    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    // Productos asociados al pago
    @ManyToMany
    @JoinTable(
            name = "pago_producto",
            joinColumns = @JoinColumn(name = "pago_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;
}
