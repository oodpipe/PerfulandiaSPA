package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity // Define esta clase como una tabla en la base de datos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    private int id;

    private String fecha;
    private String estado; // En proceso, Enviado, Entregado
    private double total;

    @ManyToOne // Relación con Usuario (cada pedido pertenece a un usuario)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany // Un pedido puede tener varios productos
    @JoinTable(
            name = "pedido_producto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;
}