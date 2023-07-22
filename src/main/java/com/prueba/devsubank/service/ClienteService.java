package com.prueba.devsubank.service;

import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.dto.ClientePutReq;
import com.prueba.devsubank.exceptions.BankException;
import com.prueba.devsubank.util.ClienteBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Long crearCliente(ClientePostReq clientePostReq){
        Cliente cliente = ClienteBuilder.build(clientePostReq);
        cliente = clienteRepository.save(cliente);
        return cliente.getId();
    }

    public void actualizarCliente(ClientePutReq clientePutReq, Long id){
        Cliente cliente = ClienteBuilder.build(clientePutReq,id);
        cliente = clienteRepository.save(cliente);
    }

    public void editarCliente(ClientePutReq clientePutReq, Long id){
        Optional<Cliente> oldClienteO = clienteRepository.findById(id);
        if(oldClienteO.isEmpty()){
            //TODO: tirar excepcion
            throw new RuntimeException("No existe cliente");
        }
        Cliente oldCliente = oldClienteO.get();
        Cliente cliente = ClienteBuilder.build(clientePutReq,id);
        ClienteBuilder.setearCamposNoNulos(oldCliente,cliente);
        cliente = clienteRepository.save(oldCliente);
    }

    public void eliminarCliente(Long clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
        } catch (DataIntegrityViolationException e) {
            throw BankException.newBankException("01","El cliente posee una cuenta",e);
        }
    }
}
