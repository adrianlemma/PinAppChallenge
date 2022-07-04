package com.challenge.pinapp.usecases;

import com.challenge.pinapp.exceptions.ClienteException;
import com.challenge.pinapp.models.ClienteModel;

import java.time.OffsetDateTime;

import static java.util.Objects.nonNull;

public class ValidarDatosCliente {

    private static int ESPERANZA_DE_VIDA = 81;

    public void execute(ClienteModel cliente) {
        if(nonNull(cliente.getFechaProbableDeMuerte())) {
            throw new ClienteException("No ingrese la fecha probable de muerte, el sistema la calculará autmaticamente");
        }
        if((OffsetDateTime.now().getYear() - cliente.getEdad()) != cliente.getFechaDeNacimiento().getYear()) {
            throw new ClienteException("La edad no coincide con la fecha de nacimiento");
        }
        if(!cliente.getNombre().matches("^([A-Za-z]+[ ]?){1,2}$")) {
            throw new ClienteException("El formato del nombre es incorrecto");
        }
        if(!cliente.getApellido().matches("^([A-Za-z]+[ ]?){1,2}$")) {
            throw new ClienteException("El formato del apellido es incorrecto");
        }

        cliente.setFechaProbableDeMuerte(calcularFechaProbableDeMuerte(cliente.getFechaDeNacimiento()));
    }

    private OffsetDateTime calcularFechaProbableDeMuerte(OffsetDateTime fechaDeNacimiento) {
        return fechaDeNacimiento.plusYears(ESPERANZA_DE_VIDA);
    }
}
