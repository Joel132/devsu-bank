package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ReporteGetResponse;
import com.prueba.devsubank.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RequestMapping(ReporteAPI.basePath)
@RestController
public class ReporteAPI {
    Logger logger = LoggerFactory.getLogger(ReporteAPI.class);
    public static final String basePath = "/reportes";

    private final MovimientoService movimientoService;

    public ReporteAPI(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping("{clienteId}")
    public List<ReporteGetResponse> obtenerReportes(@RequestParam OffsetDateTime to,
                                                    @RequestParam OffsetDateTime t1,
                                                    @PathVariable Long clienteId){
        logger.info("Llamada al endpont GET: {}/{} para generar reportes entre los dias {} y {}",basePath,clienteId, to,t1);

        return movimientoService.generarReporte(to,t1,clienteId);
    }



}
