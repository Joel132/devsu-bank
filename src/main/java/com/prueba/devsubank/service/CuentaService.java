package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dto.CuentaPatchReq;
import com.prueba.devsubank.dto.CuentaPostReq;
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

    public Long crearCuenta(CuentaPostReq cuentaPostReq, Long clienteId){
        logger.debug("Obteniendo cliente {}",clienteId);
        Cliente cliente = obtenerCliente(clienteId);
        logger.debug("Cliente obtenido {}",cliente);
        if(!cliente.getActivo()){
            throw new BankException("Cliente no esta activo");
        }
        Cuenta cuenta = CuentaBuilder.build(cuentaPostReq,cliente);
        cuenta = cuentaRepository.save(cuenta);
        logger.debug("Cuenta creada con exito: {}",cuenta);
        return cuenta.getId();
    }

    public void modificarCuenta(Long cuentaId, CuentaPatchReq cuentaPatchReq){
        logger.debug("Obteniendo cuenta {}",cuentaId);
        Cuenta oldCuenta = obtenerCuenta(cuentaId);
        logger.debug("Cuenta obtenida {}",oldCuenta);
        if(!oldCuenta.getCliente().getActivo()){
            throw new BankException("Cliente no esta activo");
        }
        oldCuenta = CuentaBuilder.setearCamposNoNulos(oldCuenta, cuentaPatchReq);
        logger.debug("Cuenta a actualizar con estos valores: {}", oldCuenta);
        cuentaRepository.save(oldCuenta);
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
            throw new BankException("No se encuentra cliente");
        }
        Cliente cliente = clienteO.get();
        return cliente;
    }

    private Cuenta obtenerCuenta(Long cuentaId) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuentaId);
        if(cuentaOptional.isEmpty()){
            throw new BankException("No se encuentra cuenta");
        }
        return cuentaOptional.get();
    }
}
