
package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoJpaRepository extends JpaRepository<Pago, Integer> {
    // Buscar pagos por estado (ej: pagado, por pagar)
    List<Pago> findByEstado(String estado);
    // Buscar pagos por ID de usuario
    List<Pago> findByUsuarioId(int usuarioId);
}
