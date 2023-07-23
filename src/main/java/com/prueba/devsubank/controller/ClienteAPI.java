package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.dto.ClientePutReq;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.service.ClienteService;
import com.prueba.devsubank.service.CuentaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping(ClienteAPI.basePath)
@RestController
public class ClienteAPI {

    Logger logger = LoggerFactory.getLogger(ClienteAPI.class);
    public static final String basePath = "/clientes";
    private final ClienteService clienteService;
    private final CuentaService cuentaService;

    public ClienteAPI(ClienteService clienteService, CuentaService cuentaService) {
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
    }


    @PostMapping()
    public ResponseEntity crearCliente(@Valid  @RequestBody ClientePostReq clientePostReq){
        logger.info("Llamada al endpont POST: {} con el body [{}]",basePath, clientePostReq);

        Long id = clienteService.crearCliente(clientePostReq);
        return ResponseEntity
                .created(URI.create(String.join("/",basePath,String.valueOf(id))))
                .build();
    }

    @PostMapping("{clienteId}/cuentas")
    public ResponseEntity crearCuenta(@Valid @RequestBody CuentaPostReq cuentaPostReq,
                                                     @PathVariable Long clienteId){

        logger.info("Llamada al endpont POST: {}/{}/cuentas con el body [{}]",basePath,clienteId, cuentaPostReq);

        Long id = cuentaService.crearCuenta(cuentaPostReq,clienteId);
        return ResponseEntity
                .created(URI.create(String.join("/",CuentaAPI.basePath,String.valueOf(id))))
                .build();
    }

    @PutMapping("{clienteId}")
    public ResponseEntity actualizarCliente(@Valid @RequestBody ClientePutReq clientePutReq,
                                            @PathVariable Long clienteId){
        logger.info("Llamada al endpont PUT: {} para actualizar el cliente: {} con el body [{}]",basePath,clienteId, clientePutReq);

        clienteService.actualizarCliente(clientePutReq,clienteId);
        return ResponseEntity
                .ok()
                .build();
    }

    @PatchMapping("{clienteId}")
    public ResponseEntity editarCliente(@RequestBody ClientePutReq clientePutReq,
                                            @PathVariable Long clienteId){
        logger.info("Llamada al endpont PUT: {} para actualizar el cliente: {} con el body [{}]",basePath,clienteId, clientePutReq);

        clienteService.editarCliente(clientePutReq,clienteId);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity eliminarCliente(@PathVariable Long clienteId){
        logger.info("Llamada al endpont DELETE: {} para eliminar el cliente: {}",basePath,clienteId);
        clienteService.eliminarCliente(clienteId);
        return ResponseEntity
                .ok()
                .build();
    }


}
