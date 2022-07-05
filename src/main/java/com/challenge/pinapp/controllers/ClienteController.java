package com.challenge.pinapp.controllers;

import com.challenge.pinapp.exceptions.ClienteException;
import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.models.KpiDeClientes;
import com.challenge.pinapp.services.ClienteService;
import com.challenge.pinapp.usecases.CalcularKpiDeClientes;
import com.challenge.pinapp.usecases.ValidarDatosCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import static java.util.Objects.isNull;

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
    public ResponseEntity<Object> crearCliente(@Valid @RequestBody ClienteModel cliente) {
        try {
            validarDatosCliente.execute(cliente);
            return ResponseEntity.ok(clienteServices.guardarCliente(cliente));
        } catch(ClienteException e) {
            return ResponseEntity.badRequest().body(e);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    isNull(e.getMessage()) || e.getMessage().isEmpty()? "Error interno, verifique los datos y vuelva a intentarlo" : e.getMessage()
            );
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
            KpiDeClientes kpi = calcularKpiDeClientes.execute();
            DecimalFormat decimalFormat = new DecimalFormat("#.00000");
            String desviacion = decimalFormat.format(kpi.getDesviacionEstandar().doubleValue());
            String promedio = decimalFormat.format(kpi.getEdadPromedio().doubleValue());
            kpi.setDesviacionEstandar(new BigDecimal(desviacion.replace(",", ".")));
            kpi.setEdadPromedio(new BigDecimal(promedio.replace(",", ".")));
            return ResponseEntity.ok(kpi);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
