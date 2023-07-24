package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dto.CuentaPatchReq;
import com.prueba.devsubank.dto.CuentaPostReq;
import com.prueba.devsubank.dto.CuentaResponse;
import com.prueba.devsubank.exceptions.BankException;
import com.prueba.devsubank.util.CuentaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaService {
    Logger logger = LoggerFactory.getLogger(CuentaService.class);

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    public CuentaResponse crearCuenta(CuentaPostReq cuentaPostReq, Long clienteId){
        try {
            logger.debug("Obteniendo cliente {}",clienteId);
            Cliente cliente = obtenerCliente(clienteId);
            logger.debug("Cliente obtenido {}",cliente);
            if(!cliente.getActivo()){
                throw BankException.newBankException("00","Cliente no esta activo");
            }
            Cuenta cuenta = CuentaBuilder.build(cuentaPostReq,cliente);
            cuenta = cuentaRepository.save(cuenta);
            logger.debug("Cuenta creada con exito: {}",cuenta);
            return CuentaBuilder.build(cuenta);
        } catch (DataIntegrityViolationException ex) {
            throw BankException.newBankException("00","Numero de cuenta ya existe");
        }
    }

    public CuentaResponse modificarCuenta(Long cuentaId, CuentaPatchReq cuentaPatchReq){
        try {
            logger.debug("Obteniendo cuenta {}",cuentaId);
            Cuenta oldCuenta = obtenerCuenta(cuentaId);
            logger.debug("Cuenta obtenida {}",oldCuenta);
            if(!oldCuenta.getCliente().getActivo()){
                throw BankException.newBankException("00","Cliente no esta activo");
            }
            oldCuenta = CuentaBuilder.setearCamposNoNulos(oldCuenta, cuentaPatchReq);
            logger.debug("Cuenta a actualizar con estos valores: {}", oldCuenta);
            oldCuenta = cuentaRepository.save(oldCuenta);
            return CuentaBuilder.build(oldCuenta);
        } catch (DataIntegrityViolationException ex) {
            throw BankException.newBankException("00","Numero de cuenta ya existe");
        }
    }

    public void eliminarCuenta(Long cuentaId) {
        Cuenta cuenta = obtenerCuenta(cuentaId);
        try {
            cuentaRepository.delete(cuenta);
        } catch (DataIntegrityViolationException e) {
            throw BankException.newBankException("01","La cuenta tiene movimientos",e);
        }
    }

    private Cliente obtenerCliente(Long clienteId) {
        Optional<Cliente> clienteO = clienteRepository.findById(clienteId);
        if(clienteO.isEmpty()){
            throw BankException.newBankException("00","No se encuentra cliente");
        }
        Cliente cliente = clienteO.get();
        return cliente;
    }

    private Cuenta obtenerCuenta(Long cuentaId) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuentaId);
        if(cuentaOptional.isEmpty()){
            throw BankException.newBankException("00","No se encuentra cuenta");
        }
        return cuentaOptional.get();
    }
}
