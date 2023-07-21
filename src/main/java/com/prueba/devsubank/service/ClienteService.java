package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.util.ClienteBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void crearCliente(ClientePostReq clientePostReq){
        Cliente cliente = ClienteBuilder.build(clientePostReq);
        clienteRepository.save(cliente);
    }
}
