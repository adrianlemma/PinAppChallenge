package com.challenge.pinapp.models;

import java.math.BigDecimal;

public class KpiDeClientes {

    private BigDecimal edadPromedio;

    private BigDecimal desviacionEstandar;

    //Constructors

    public KpiDeClientes() { }

    public KpiDeClientes(BigDecimal edadPromedio, BigDecimal desviacionEstandar) {
        this.edadPromedio = edadPromedio;
        this.desviacionEstandar = desviacionEstandar;
    }

    //Getters y Setters

    public BigDecimal getEdadPromedio() {
        return edadPromedio;
    }

    public void setEdadPromedio(BigDecimal edadPromedio) {
        this.edadPromedio = edadPromedio;
    }

    public BigDecimal getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setDesviacionEstandar(BigDecimal desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }
}
