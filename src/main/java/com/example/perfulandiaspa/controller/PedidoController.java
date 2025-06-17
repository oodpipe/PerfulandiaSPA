package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Pedido;
import com.example.perfulandiaspa.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return new ResponseEntity<>(pedidoService.getAllPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
        Pedido pedido = pedidoService.getPedidoById(id);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Corregido: buscar por clienteId, no usuarioId
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> getPedidosByCliente(@PathVariable int clienteId) {
        return new ResponseEntity<>(pedidoService.getPedidosByCliente(clienteId), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> getPedidosByEstado(@PathVariable String estado) {
        return new ResponseEntity<>(pedidoService.getPedidosByEstado(estado), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.createPedido(pedido), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        Pedido updated = pedidoService.updatePedido(pedido);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}/estado/{nuevoEstado}")
    public ResponseEntity<Pedido> updateEstadoPedido(@PathVariable int id, @PathVariable String nuevoEstado) {
        Pedido updated = pedidoService.updateEstadoPedido(id, nuevoEstado);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
        if (pedidoService.deletePedido(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}