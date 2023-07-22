package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.dto.ClientePutReq;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.service.ClienteService;
import com.prueba.devsubank.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping(ClienteAPI.basePath)
@RestController
public class ClienteAPI {

    public static final String basePath = "/clientes";
    private final ClienteService clienteService;
    private final CuentaService cuentaService;

    public ClienteAPI(ClienteService clienteService, CuentaService cuentaService) {
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
    }


    @PostMapping()
    public ResponseEntity<ClientePostReq> crearCliente(@RequestBody ClientePostReq clientePostReq){

        Long id = clienteService.crearCliente(clientePostReq);
        return ResponseEntity
                .created(URI.create(String.join("/",basePath,String.valueOf(id))))
                .build();
    }

    @PostMapping("{clienteId}/cuentas")
    public ResponseEntity<CuentaPostReq> crearCuenta(@RequestBody CuentaPostReq cuentaPostReq,
                                                     @PathVariable Long clienteId){

        Long id = cuentaService.crearCuenta(cuentaPostReq,clienteId);
        return ResponseEntity
                .created(URI.create(String.join("/",CuentaAPI.basePath,String.valueOf(id))))
                .build();
    }

    @PutMapping("{clienteId}")
    public ResponseEntity actualizarCliente(@RequestBody ClientePutReq clientePutReq,
                                            @PathVariable Long clienteId){
        clienteService.actualizarCliente(clientePutReq,clienteId);
        return ResponseEntity
                .ok()
                .build();
    }

    @PatchMapping("{clienteId}")
    public ResponseEntity editarCliente(@RequestBody ClientePutReq clientePutReq,
                                            @PathVariable Long clienteId){
        clienteService.editarCliente(clientePutReq,clienteId);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity eliminarCliente(@PathVariable Long clienteId){
        clienteService.eliminarCliente(clienteId);
        return ResponseEntity
                .ok()
                .build();
    }


}
