package com.challenge.pinapp.services;

import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @InjectMocks
    ClienteService useCase;

    @Mock
    ClienteRepository clienteRepository;

    private ClienteModel cliente;

    @BeforeEach
    void SetUp() {
        cliente = new ClienteModel("Marcelo","Lemma",30, OffsetDateTime.now().minusYears(30));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardadoDeCliente() {
        when(clienteRepository.save(any())).thenReturn(cliente);
        ClienteModel result = useCase.guardarCliente(cliente);
        assertNotNull(result);
        assertEquals(cliente, result);
    }

    @Test
    void testListarClientes() {
        List<ClienteModel> clientes = new ArrayList<>();
        clientes.add(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);
        List<ClienteModel> result = useCase.listarClientes();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
