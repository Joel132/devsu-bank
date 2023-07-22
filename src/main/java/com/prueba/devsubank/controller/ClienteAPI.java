package com.prueba.devsubank.controller;

import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/clientes")
@RestController
public class ClienteAPI {

    private final ClienteService clienteService;

    public ClienteAPI(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping()
    public ResponseEntity<ClientePostReq> getCliente(@RequestBody ClientePostReq clientePostReq){

        clienteService.crearCliente(clientePostReq);
        return ResponseEntity.created(URI.create("clientes")).build();
    }


}
