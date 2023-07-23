package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.MovimientoPutReq;
import com.prueba.devsubank.service.MovimientoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(MovimientoAPI.basePath)
@RestController
public class MovimientoAPI {
    Logger logger = LoggerFactory.getLogger(MovimientoAPI.class);
    public static final String basePath = "/movimientos";

    private final MovimientoService movimientoService;

    public MovimientoAPI(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @DeleteMapping("{movimientoId}")
    public ResponseEntity eliminarMovimiento(@PathVariable Long movimientoId){
        logger.info("Llamada al endpont DELETE: {} para eliminar movimiento: {}",basePath,movimientoId);

        movimientoService.eliminarMovimiento(movimientoId);
        return ResponseEntity
                .ok()
                .build();
    }

    @PutMapping("{movimientoId}")
    public ResponseEntity modificarMovimiento(@PathVariable Long movimientoId, @RequestBody @Valid MovimientoPutReq movimientoPutReq){
        logger.info("Llamada al endpont PUT: {}/{} para modificar movimiento con los siguientes datos: {}",basePath,movimientoId,movimientoPutReq);

        movimientoService.modificarMovimiento(movimientoId, movimientoPutReq);
        return ResponseEntity
                .ok()
                .build();
    }

}
