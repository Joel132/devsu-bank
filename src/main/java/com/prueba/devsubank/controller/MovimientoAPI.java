package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ReporteGetResponse;
import com.prueba.devsubank.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RequestMapping(MovimientoAPI.basePath)
@RestController
public class MovimientoAPI {
    public static final String basePath = "/movimientos";

    private final MovimientoService movimientoService;

    public MovimientoAPI(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @DeleteMapping("{movimientoId}")
    public ResponseEntity eliminarMovimiento(@PathVariable Long movimientoId){
        movimientoService.eliminarMovimiento(movimientoId);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/reportes/{clienteId}")
    public List<ReporteGetResponse> obtenerReportes(@RequestParam OffsetDateTime to,
                                                    @RequestParam OffsetDateTime t1,
                                                    @PathVariable Long clienteId){
        return movimientoService.generarReporte(to,t1,clienteId);
    }



}
