package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ReporteGetResponse;
import com.prueba.devsubank.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

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
        logger.info("Llamada al endpont DELETE: {} para eliminar el cliente: {}",basePath,movimientoId);

        movimientoService.eliminarMovimiento(movimientoId);
        return ResponseEntity
                .ok()
                .build();
    }

}
