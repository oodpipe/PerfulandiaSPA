package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    private int id;

    private String nombre;
    private String email;
    private String direccion;
    private String telefono;

    @ManyToOne // Muchos clientes pueden pertenecer a una sucursal
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}
