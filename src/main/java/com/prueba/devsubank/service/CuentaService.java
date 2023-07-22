package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.exceptions.BankException;
import com.prueba.devsubank.util.CuentaBuilder;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Long crearCuenta(CuentaPostReq cuentaPostReq, Long clienteId){
        Cliente cliente = obtenerCliente(clienteId);
        Cuenta cuenta = CuentaBuilder.build(cuentaPostReq,cliente);
        cuenta = cuentaRepository.save(cuenta);
        return cuenta.getId();
    }

    private Cliente obtenerCliente(Long clienteId) {
        Optional<Cliente> clienteO = clienteRepository.findById(clienteId);
        if(clienteO.isEmpty()){
            //TODO: Lanzar error
            throw new RuntimeException("No se encuentra cliente");
        }
        Cliente cliente = clienteO.get();
        return cliente;
    }

    public void eliminarCuenta(Long cuentaId) {
        try {
            cuentaRepository.deleteById(cuentaId);
        } catch (DataIntegrityViolationException e) {
            throw BankException.newBankException("01","La cuenta tiene movimientos",e);
        }
    }
}
