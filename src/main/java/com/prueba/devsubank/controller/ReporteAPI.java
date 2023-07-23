package com.prueba.devsubank.controller;

import com.prueba.devsubank.dto.ReporteGetResponse;
import com.prueba.devsubank.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<List<ReporteGetResponse>> obtenerReportes(@RequestParam(required = true) LocalDate to,
                                                    @RequestParam(required = false) LocalDate t1,
                                                    @PathVariable Long clienteId){
        logger.info("Llamada al endpont GET: {}/{} para generar reportes entre los dias {} y {}",basePath,clienteId, to,t1);
        if(t1==null) t1=to;

        List<ReporteGetResponse> reporteGetResponses = movimientoService.generarReporte(to, t1, clienteId);

        if(CollectionUtils.isEmpty(reporteGetResponses)){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(reporteGetResponses);
    }



}
