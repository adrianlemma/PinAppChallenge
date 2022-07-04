package com.challenge.pinapp.usecase;

import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.models.KpiDeClientes;
import com.challenge.pinapp.services.ClienteService;
import com.challenge.pinapp.usecases.CalcularKpiDeClientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CalcularKpiDeClientesTest {

    @InjectMocks
    CalcularKpiDeClientes useCase;

    @Mock
    ClienteService clienteServices;

    @BeforeEach
    void SetUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCalculoCorrectoDelKpiConListaDeClientesVacia() {
        when(clienteServices.listarClientes()).thenReturn(new ArrayList<ClienteModel>());
        KpiDeClientes kpi = useCase.execute();
        assertNotNull(kpi);
        assertEquals(new BigDecimal(0), kpi.getEdadPromedio());
        assertEquals(new BigDecimal(0), kpi.getDesviacionEstandar());
    }

    @Test
    void testCalculoCorrectoCuandoLaListaNoEstaVacia() {
        List<ClienteModel> clientes = new ArrayList<ClienteModel>();
        clientes.add(new ClienteModel("test1","test1",30, OffsetDateTime.now().minusYears(30)));
        clientes.add(new ClienteModel("test2","test2",30, OffsetDateTime.now().minusYears(30)));
        when(clienteServices.listarClientes()).thenReturn(clientes);
        KpiDeClientes kpi = useCase.execute();
        assertNotNull(kpi);
        assertEquals(new BigDecimal(30), kpi.getEdadPromedio());
        assertEquals(new BigDecimal(0), kpi.getDesviacionEstandar());
    }
}
