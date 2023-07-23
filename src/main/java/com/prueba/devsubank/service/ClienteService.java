package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.dto.ClientePutReq;
import com.prueba.devsubank.exceptions.BankException;
import com.prueba.devsubank.util.ClienteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Long crearCliente(ClientePostReq clientePostReq){
        Cliente cliente = ClienteBuilder.build(clientePostReq);
        cliente = clienteRepository.save(cliente);
        logger.debug("Cliente agregado con exito [{}]",cliente);
        return cliente.getId();
    }

    public void actualizarCliente(ClientePutReq clientePutReq, Long id){
        Cliente cliente = ClienteBuilder.build(clientePutReq,id);
        cliente = clienteRepository.save(cliente);
        logger.debug("Cliente actualizado con exito [{}]",cliente);
    }

    public void editarCliente(ClientePutReq clientePutReq, Long id){
        Optional<Cliente> oldClienteO = clienteRepository.findById(id);
        if(oldClienteO.isEmpty()){
            //TODO: tirar excepcion
            logger.error("No existe cliente");
            throw new BankException("No existe cliente");
        }
        Cliente oldCliente = oldClienteO.get();
        Cliente cliente = ClienteBuilder.build(clientePutReq,id);
        ClienteBuilder.setearCamposNoNulos(oldCliente,cliente);
        cliente = clienteRepository.save(oldCliente);
        logger.debug("Cliente actualizado con exito [{}]",cliente);
    }

    public void eliminarCliente(Long clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error al intentar eliminar cliente", e);
            throw BankException.newBankException("01","El cliente posee una cuenta",e);
        }
    }
}
