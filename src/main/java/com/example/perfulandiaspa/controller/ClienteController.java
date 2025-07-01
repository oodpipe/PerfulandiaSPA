package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Cliente;
import com.example.perfulandiaspa.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.crearCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cliente>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(clienteService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {
        Cliente actualizado = clienteService.actualizarCliente(id, cliente);
        return actualizado != null ?
                ResponseEntity.ok(actualizado) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        return clienteService.eliminarCliente(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    // Nuevos endpoints para perfumería
    @PostMapping("/{id}/registrar-compra")
    public ResponseEntity<Void> registrarCompraPerfumeria(
            @PathVariable Long id,
            @RequestParam String detalleCompra) {
        clienteService.registrarCompraEnHistorial(id, detalleCompra);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/compras")
    public ResponseEntity<List<String>> obtenerComprasCliente(@PathVariable Long id) {
        return ResponseEntity.of(clienteService.obtenerComprasDelCliente(id));
    }
}