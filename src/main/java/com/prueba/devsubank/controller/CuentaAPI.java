package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/cuentas")
@RestController
public class CuentaAPI {

    private final CuentaService cuentaService;

    public CuentaAPI(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }


    @PostMapping()
    public ResponseEntity<CuentaPostReq> getCliente(@RequestBody CuentaPostReq cuentaPostReq){

        cuentaService.crearCuenta(cuentaPostReq);
        return ResponseEntity
                .created(URI.create("cuentas"))
                .build();
    }


}
