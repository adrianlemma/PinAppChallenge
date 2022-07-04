package com.challenge.pinapp.usecases;

import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.models.KpiDeClientes;
import com.challenge.pinapp.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class CalcularKpiDeClientes {

    @Autowired
    private ClienteServices clienteServices;

    public KpiDeClientes execute() {
        List<ClienteModel> clientes = clienteServices.listarClientes();
        int sumaEdades = clientes.stream().mapToInt(ClienteModel::getEdad).sum();
        double edadPromedio = 0.0;
        double desviacionEstandar = 0.0;
        if(!clientes.isEmpty()) {
            edadPromedio = (double) sumaEdades / clientes.size();
            double finalEdadPromedio = edadPromedio;
            desviacionEstandar = clientes.stream().mapToDouble(cli -> Math.pow((cli.getEdad() - finalEdadPromedio), 2)).sum();
            desviacionEstandar = Math.sqrt(desviacionEstandar / (clientes.size() - 1));
        }
        return new KpiDeClientes(new BigDecimal(edadPromedio), new BigDecimal(desviacionEstandar));
    }
}
