package com.example.perfulandiaspa.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    private int id;
    private int usuarioId; // Relacionado con Usuario
    private String fecha;
    private String estado; // por pagar, pagado
    private List<Producto> productos;
    private double total;
    private int pedidoId; // Relacionado con Pedido
}