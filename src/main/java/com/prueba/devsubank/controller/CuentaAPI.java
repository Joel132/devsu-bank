package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.service.CuentaService;
import com.prueba.devsubank.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping(CuentaAPI.basePath)
@RestController
public class CuentaAPI {

    public static final String basePath = "/cuentas";
    private final CuentaService cuentaService;

    private final MovimientoService movimientoService;

    public CuentaAPI(CuentaService cuentaService, MovimientoService movimientoService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
    }

    @PostMapping("{cuentaId}/movimientos")
    public ResponseEntity<CuentaPostReq> agregarMovimientos(@RequestBody MovimientoPostReq movimientoPostReq,
                                                            @PathVariable Long cuentaId){

        Long id = movimientoService.registrarMovimiento(movimientoPostReq,cuentaId);
        return ResponseEntity
                .created(URI.create(String.join("/",MovimientoAPI.basePath,String.valueOf(id))))
                .build();
    }

    @DeleteMapping("{cuentaId}")
    public ResponseEntity eliminarCuenta(@PathVariable Long cuentaId){
        cuentaService.eliminarCuenta(cuentaId);
        return ResponseEntity
                .ok()
                .build();
    }





}
