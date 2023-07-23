package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.ReporteGetResponse;
import com.prueba.devsubank.enums.TipoMovimiento;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReporteBuilder {
    public static List<ReporteGetResponse> build(List<Movimiento> movimientos) {
        if(CollectionUtils.isEmpty(movimientos)) return Collections.emptyList();

        return movimientos.stream().map(movimiento -> build(movimiento)).collect(Collectors.toList());

    }

    public static ReporteGetResponse build(Movimiento movimiento) {
        ReporteGetResponse reporteGetResponse = new ReporteGetResponse();
        reporteGetResponse.setFechaMovimiento(movimiento.getFecha());
        reporteGetResponse.setNombreCliente(movimiento.getCuenta().getCliente().getNombre());
        reporteGetResponse.setNumeroCuenta(movimiento.getCuenta().getNumero());
        reporteGetResponse.setTipoCuenta(movimiento.getCuenta().getTipo());
        reporteGetResponse.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
        reporteGetResponse.setSaldoDisponible(movimiento.getSaldo());
        reporteGetResponse.setMovimiento(String.format("%s de %s",movimiento.getTipo(),movimiento.getTipo().equals(TipoMovimiento.DEBITO)?movimiento.getValor().negate():movimiento.getValor()));
        return reporteGetResponse;

    }
}
