package com.example.perfulandiaspa.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int id;
    private int usuarioId;
    private String fecha;
    private String estado; // En proceso, Enviado, Entregado
    private List<Producto> productos;
    private double total;
}