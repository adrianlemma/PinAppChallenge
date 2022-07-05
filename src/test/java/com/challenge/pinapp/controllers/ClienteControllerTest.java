package com.challenge.pinapp.controllers;

import com.challenge.pinapp.exceptions.ClienteException;
import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.models.KpiDeClientes;
import com.challenge.pinapp.services.ClienteService;
import com.challenge.pinapp.usecases.CalcularKpiDeClientes;
import com.challenge.pinapp.usecases.ValidarDatosCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {

    @InjectMocks
    ClienteController useCase;

    @Mock
    private ClienteService clienteServices;

    @Mock
    private ValidarDatosCliente validarDatosCliente;

    @Mock
    private CalcularKpiDeClientes calcularKpiDeClientes;

    private ClienteModel cliente;

    @BeforeEach
    void SetUp() {
        cliente = new ClienteModel("Marcelo", "Lemma", 30, OffsetDateTime.now().minusYears(30));
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    class TestGuardarCliente {
        @Test
        void testGuardarClienteCorrectamente() {
            doNothing().when(validarDatosCliente).execute(any());
            ResponseEntity<Object> result = useCase.crearCliente(cliente);
            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
        }

        @Test
        void testGuardarClienteLanzaExcepcionPorRequest() {
            doThrow(ClienteException.class).when(validarDatosCliente).execute(cliente);
            ResponseEntity<Object> result = useCase.crearCliente(cliente);
            assertNotNull(result);
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        }

        @Test
        void testGuardarClienteLanzaExcepcionInterna() {
            doThrow(RestClientException.class).when(validarDatosCliente).execute(cliente);
            ResponseEntity<Object> result = useCase.crearCliente(cliente);
            assertNotNull(result);
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        }
    }

    @Nested
    class TestListarClientes {
        @Test
        void testListarClientesCorrectamente() {
            ResponseEntity<Object> result = useCase.listClientes();
            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
        }

        @Test
        void testListarClientesLanzaExcepcion() {
            doThrow(RestClientException.class).when(clienteServices).listarClientes();
            ResponseEntity<Object> result = useCase.listClientes();
            assertNotNull(result);
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        }
    }

    @Nested
    class TestKpiDeClientes {
        @Test
        void testCalcularKpiCorrectamente() {
            KpiDeClientes kpi = new KpiDeClientes();
            kpi.setEdadPromedio(new BigDecimal("30.123456"));
            kpi.setDesviacionEstandar(new BigDecimal("1.123456"));
            when(calcularKpiDeClientes.execute()).thenReturn(kpi);
            ResponseEntity<Object> result = useCase.kpiDeClientes();
            assertNotNull(result);
            assertEquals(HttpStatus.OK, result.getStatusCode());
        }

        @Test
        void testCalcularKpiLanzaExcepcion() {
            doThrow(ClienteException.class).when(calcularKpiDeClientes).execute();
            ResponseEntity<Object> result = useCase.kpiDeClientes();
            assertNotNull(result);
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        }
    }
}
