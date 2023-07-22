package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/movimientos")
@RestController
public class MovimientoAPI {

    private final MovimientoService movimientoService;

    public MovimientoAPI(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }


    @PostMapping()
    public ResponseEntity<CuentaPostReq> saveMovimientos(@RequestBody MovimientoPostReq movimientoPostReq){

        movimientoService.registrarMovimiento(movimientoPostReq);
        return ResponseEntity
                .created(URI.create("movimientos"))
                .build();
    }


}
