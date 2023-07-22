package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.util.CuentaBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    public void crearCuenta(CuentaPostReq cuentaPostReq){
        Cuenta cuenta = CuentaBuilder.build(cuentaPostReq);
        Optional<Cliente> clienteO = clienteRepository.findClienteByClienteId(cuentaPostReq.getClienteId());
        if(clienteO.isEmpty()){
            //TODO: Lanzar error
            throw new RuntimeException("No se encuentra cliente");
        }
        cuenta.setCliente(clienteO.get());
        cuentaRepository.save(cuenta);
    }
}
