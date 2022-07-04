package com.challenge.pinapp.services;

import com.challenge.pinapp.models.ClienteModel;
import com.challenge.pinapp.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> listarClientes() {
        return (List<ClienteModel>) clienteRepository.findAll();
    }

    public ClienteModel guardarCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }
}
