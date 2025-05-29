package com.example.perfulandiaspa.repository;

import com.example.perfulandiaspa.model.Pago;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PagoRepository {
    private final List<Pago> pagos = new ArrayList<>();

    public List<Pago> findAll() {
        return pagos;
    }

    public Optional<Pago> findById(int id) {
        return pagos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void save(Pago pago) {
        pagos.add(pago);
    }

    public void deleteById(int id) {
        pagos.removeIf(p -> p.getId() == id);
    }
}