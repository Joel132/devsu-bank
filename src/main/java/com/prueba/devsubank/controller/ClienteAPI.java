package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.*;
import com.prueba.devsubank.service.ClienteService;
import com.prueba.devsubank.service.CuentaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ClienteResponse> crearCliente(@Valid  @RequestBody ClientePostReq clientePostReq){
        logger.info("Llamada al endpont POST: {} con el body [{}]",basePath, clientePostReq);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteService.crearCliente(clientePostReq));
    }

    @PostMapping("{clienteId}/cuentas")
    public ResponseEntity<CuentaResponse> crearCuenta(@Valid @RequestBody CuentaPostReq cuentaPostReq,
                                                      @PathVariable Long clienteId){

        logger.info("Llamada al endpont POST: {}/{}/cuentas con el body [{}]",basePath,clienteId, cuentaPostReq);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cuentaService.crearCuenta(cuentaPostReq,clienteId));
    }

    @PutMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> actualizarCliente(@Valid @RequestBody ClientePutReq clientePutReq,
                                            @PathVariable Long clienteId){
        logger.info("Llamada al endpont PUT: {} para actualizar el cliente: {} con el body [{}]",basePath,clienteId, clientePutReq);

        return ResponseEntity
                .ok()
                .body(clienteService.actualizarCliente(clientePutReq,clienteId));
    }

    @PatchMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> editarCliente(@RequestBody ClientePutReq clientePutReq,
                                            @PathVariable Long clienteId){
        logger.info("Llamada al endpont PUT: {} para actualizar el cliente: {} con el body [{}]",basePath,clienteId, clientePutReq);
        return ResponseEntity
                .ok()
                .body(clienteService.editarCliente(clientePutReq,clienteId));
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
