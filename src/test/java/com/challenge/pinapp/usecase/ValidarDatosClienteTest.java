package com.challenge.pinapp.usecase;

import com.challenge.pinapp.exceptions.ClienteException;
import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.usecases.ValidarDatosCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidarDatosClienteTest {

    @InjectMocks
    ValidarDatosCliente useCase;

    private ClienteModel cliente;

    @BeforeEach
    void SetUp() {
        cliente = new ClienteModel("Marcelo","Lemma",30, OffsetDateTime.now().minusYears(30));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTodosLosDatosSonCorrectos() {
        useCase.execute(cliente);
        assertEquals(cliente.getFechaDeNacimiento().plusYears(81), cliente.getFechaProbableDeMuerte());
    }

    @Test
    void testFormatoDeApellidoIncorrecto() {
        cliente.setApellido("2?a/*X");
        assertThrows(ClienteException.class, () -> useCase.execute(cliente));
    }

    @Test
    void testFormatoDeNombreIncorrecto() {
        cliente.setNombre("2?a/*X");
        assertThrows(ClienteException.class, () -> useCase.execute(cliente));
    }

    @Test
    void testEdadNoCoincideConFechaDeNacimiento() {
        cliente.setEdad(10);
        assertThrows(ClienteException.class, () -> useCase.execute(cliente));
    }

    @Test
    void testSeIntentaForzarFechaProbableDeMuerte() {
        cliente.setFechaProbableDeMuerte(OffsetDateTime.now());
        assertThrows(ClienteException.class, () -> useCase.execute(cliente));
    }
}
