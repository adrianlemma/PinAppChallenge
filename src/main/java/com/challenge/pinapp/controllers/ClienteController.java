package com.challenge.pinapp.controllers;

import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.models.KpiDeClientes;
import com.challenge.pinapp.services.ClienteServices;
import com.challenge.pinapp.usecases.CalcularKpiDeClientes;
import com.challenge.pinapp.usecases.ValidarDatosCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    private ValidarDatosCliente validarDatosCliente;

    private CalcularKpiDeClientes calcularKpiDeClientes;

    @PostMapping("/crearcliente")
    public ClienteModel crearCliente(@RequestBody ClienteModel cliente) {
        validarDatosCliente.execute(cliente);
        return clienteServices.guardarCliente(cliente);
    }

    @GetMapping("/listclientes")
    public List<ClienteModel> listClientes() {
        return clienteServices.listarClientes();
    }

    @GetMapping("/kpideclientes")
    public KpiDeClientes kpiDeClientes() {
        return calcularKpiDeClientes.execute();
    }
}
