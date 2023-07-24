package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.CuentaPatchReq;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.dto.MovimientoResponse;
import com.prueba.devsubank.service.CuentaService;
import com.prueba.devsubank.service.MovimientoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping(CuentaAPI.basePath)
@RestController
public class CuentaAPI {
    Logger logger = LoggerFactory.getLogger(CuentaAPI.class);

    public static final String basePath = "/cuentas";
    private final CuentaService cuentaService;

    private final MovimientoService movimientoService;

    public CuentaAPI(CuentaService cuentaService, MovimientoService movimientoService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
    }

    @PostMapping("{cuentaId}/movimientos")
    public ResponseEntity<MovimientoResponse> agregarMovimientos(@Valid  @RequestBody MovimientoPostReq movimientoPostReq,
                                                                 @PathVariable Long cuentaId){
        logger.info("Llamada al endpont POST: {}/{}/movimientos con el body [{}]",basePath,cuentaId, movimientoPostReq);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movimientoService.registrarMovimiento(movimientoPostReq,cuentaId));
    }

    @PatchMapping("{cuentaId}")
    public ResponseEntity modificarCuenta(@PathVariable Long cuentaId, @RequestBody CuentaPatchReq cuentaPatchReq){
        logger.info("Llamada al endpont PATCH: {}/{} con el body [{}]",basePath,cuentaId, cuentaPatchReq);
        return ResponseEntity
                .ok()
                .body(cuentaService.modificarCuenta(cuentaId,cuentaPatchReq));
    }

    @DeleteMapping("{cuentaId}")
    public ResponseEntity eliminarCuenta(@PathVariable Long cuentaId){
        logger.info("Llamada al endpont DELETE: {}/{}",basePath,cuentaId);
        cuentaService.eliminarCuenta(cuentaId);
        return ResponseEntity
                .ok()
                .build();
    }





}
