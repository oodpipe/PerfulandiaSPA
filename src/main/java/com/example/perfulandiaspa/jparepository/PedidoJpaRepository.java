package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoJpaRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByUsuario_Id(int usuarioId); // "_Id" accede al id del usuario
}