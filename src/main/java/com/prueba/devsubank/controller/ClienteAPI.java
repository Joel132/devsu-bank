package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ClienteResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/clientes")
@RestController
public class ClienteAPI {

    @GetMapping("{clienteId}")
    public ClienteResponse getCliente(String clienteId){
        ClienteResponse cliente = new ClienteResponse();
        cliente.setNombre("Jose");
        return cliente;
    }
}
