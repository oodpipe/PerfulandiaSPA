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
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fecha;
    private String estado; // En proceso, Enviado, Entregado
    private double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id") // se cambia de usuario_id a cliente_id
    private Cliente cliente; // antes era Usuario

    @ManyToMany
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;
}