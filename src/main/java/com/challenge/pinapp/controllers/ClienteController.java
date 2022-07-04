package com.challenge.pinapp.controllers;

import com.challenge.pinapp.exceptions.ClienteException;
import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.services.ClienteService;
import com.challenge.pinapp.usecases.CalcularKpiDeClientes;
import com.challenge.pinapp.usecases.ValidarDatosCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteServices;

    @Autowired
    private ValidarDatosCliente validarDatosCliente;

    @Autowired
    private CalcularKpiDeClientes calcularKpiDeClientes;

    @PostMapping("/crearcliente")
    public ResponseEntity<Object> crearCliente(@RequestBody ClienteModel cliente) {
        try {
            validarDatosCliente.execute(cliente);
            return ResponseEntity.ok(clienteServices.guardarCliente(cliente));
        } catch(ClienteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/listclientes")
    public ResponseEntity<Object> listClientes() {
        try {
            return ResponseEntity.ok(clienteServices.listarClientes());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/kpideclientes")
    public ResponseEntity<Object> kpiDeClientes() {
        try {
            return ResponseEntity.ok(calcularKpiDeClientes.execute());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
