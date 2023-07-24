package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.dto.ClientePutReq;
import com.prueba.devsubank.dto.ClienteResponse;
import com.prueba.devsubank.exceptions.BankException;
import com.prueba.devsubank.util.ClienteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;

    public ClienteService(ClienteRepository clienteRepository, CuentaRepository cuentaRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public ClienteResponse crearCliente(ClientePostReq clientePostReq){
        try {
            Cliente cliente = ClienteBuilder.build(clientePostReq);
            cliente = clienteRepository.save(cliente);
            logger.debug("Cliente agregado con exito [{}]",cliente);
            return ClienteBuilder.build(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw BankException.newBankException("00","Combinacion de numeroDocumento y tipoDocumento invalida ");
        }
    }

    public ClienteResponse actualizarCliente(ClientePutReq clientePutReq, Long id){
        try {
            Cliente oldCliente = obtenerCliente(id);
            Cliente cliente = ClienteBuilder.build(clientePutReq,id);
            actualizarEstadoDeCuentas(id, clientePutReq.getActivo(), oldCliente.getActivo());
            cliente = clienteRepository.save(cliente);
            logger.debug("Cliente actualizado con exito [{}]",cliente);
            return  ClienteBuilder.build(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw BankException.newBankException("00","Combinacion de numeroDocumento y tipoDocumento invalida ");
        }
    }

    public ClienteResponse editarCliente(ClientePutReq clientePutReq, Long id){
        try {
            Cliente oldCliente = obtenerCliente(id);
            Cliente cliente = ClienteBuilder.build(clientePutReq,id);
            actualizarEstadoDeCuentas(id, clientePutReq.getActivo(), oldCliente.getActivo());
            ClienteBuilder.setearCamposNoNulos(oldCliente,cliente);
            cliente = clienteRepository.save(oldCliente);
            logger.debug("Cliente actualizado con exito [{}]",cliente);
            return  ClienteBuilder.build(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw BankException.newBankException("00","Combinacion de numeroDocumento y tipoDocumento invalida ");
        }
    }

    public void eliminarCliente(Long clienteId) {
        Cliente oldCliente = obtenerCliente(clienteId);
        try {
            clienteRepository.deleteById(clienteId);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error al intentar eliminar cliente", e);
            throw BankException.newBankException("01","El cliente posee una cuenta",e);
        }
    }

    private Cliente obtenerCliente(Long id) {
        Optional<Cliente> oldClienteO = clienteRepository.findById(id);
        if(oldClienteO.isEmpty()){
            logger.error("No existe cliente");
            throw BankException.newBankException("00","No existe cliente");
        }
        Cliente oldCliente = oldClienteO.get();
        return oldCliente;
    }

    /**
     * Metodo para actualizar los estados de las cuentas de un cliente, solo si el estado del cliente pasa a ser inactivo
     * @param clienteId El id del cliente
     * @param estadoNuevo El estado a actualizar del cliente
     * @param estadoViejo El estado que tenia anteriormente el cliente
     */
    private void actualizarEstadoDeCuentas(Long clienteId, Boolean estadoNuevo, Boolean estadoViejo) {
        if(Objects.nonNull(estadoNuevo) && !estadoNuevo && estadoViejo){
            cuentaRepository.updateEstadoForCuentasByCliente(estadoNuevo, clienteId);
        }
    }
}
