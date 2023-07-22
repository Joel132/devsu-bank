package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dto.CuentaPostReq;

public class CuentaBuilder {

    public static Cuenta build(CuentaPostReq cuentaPostReq) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaPostReq.getNumero());
        cuenta.setTipo(cuentaPostReq.getTipo());
        cuenta.setSaldoInicial(cuentaPostReq.getSaldoInicial());
        cuenta.setMoneda(cuentaPostReq.getMoneda());
        cuenta.setEstado(cuentaPostReq.getEstado());
        return cuenta;
    }
}
