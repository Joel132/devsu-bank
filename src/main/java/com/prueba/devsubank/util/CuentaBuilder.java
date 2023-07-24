package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dto.CuentaPatchReq;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.dto.CuentaResponse;

import java.util.Objects;

public class CuentaBuilder {

    public static Cuenta build(CuentaPostReq cuentaPostReq, Cliente cliente) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(cuentaPostReq.getNumero());
        cuenta.setTipo(cuentaPostReq.getTipo());
        cuenta.setSaldoInicial(cuentaPostReq.getSaldoInicial());
        cuenta.setMoneda(cuentaPostReq.getMoneda());
        cuenta.setActivo(cuentaPostReq.getActivo());
        cuenta.setCliente(cliente);
        return cuenta;
    }

    public static Cuenta setearCamposNoNulos(Cuenta cuenta,CuentaPatchReq cuentaPatchReq){
        if(Objects.nonNull(cuentaPatchReq.getNumero())){
            cuenta.setNumero(cuentaPatchReq.getNumero());
        }
        if(Objects.nonNull(cuentaPatchReq.getTipo())){
            cuenta.setTipo(cuentaPatchReq.getTipo());
        }
        if(Objects.nonNull(cuentaPatchReq.getActivo())){
            cuenta.setActivo(cuentaPatchReq.getActivo());
        }
        return cuenta;
    }

    public static CuentaResponse build(Cuenta cuenta) {
        CuentaResponse cuentaResponse = new CuentaResponse();
        cuentaResponse.setId(cuenta.getId());
        cuentaResponse.setNumero(cuenta.getNumero());
        cuentaResponse.setTipo(cuenta.getTipo());
        cuentaResponse.setActivo(cuenta.getActivo());
        cuentaResponse.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaResponse.setMoneda(cuenta.getMoneda());
        return cuentaResponse;
    }
}
