package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dto.CuentaPostReq;

public class CuentaBuilder {

    public static Cuenta build(CuentaPostReq cuentaPostReq, Cliente cliente) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaPostReq.getNumero());
        cuenta.setTipo(cuentaPostReq.getTipo());
        cuenta.setSaldoInicial(cuentaPostReq.getSaldoInicial());
        cuenta.setMoneda(cuentaPostReq.getMoneda());
        cuenta.setEstado(cuentaPostReq.getEstado());
        cuenta.setCliente(cliente);
        return cuenta;
    }
}
