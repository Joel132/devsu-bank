package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dto.ClientePostReq;
import com.prueba.devsubank.dto.ClientePutReq;
import com.prueba.devsubank.dto.ClienteResponse;

import java.util.Objects;

public class ClienteBuilder {

    public static Cliente build(ClientePostReq clientePostReq) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clientePostReq.getNombre());
        cliente.setEdad(clientePostReq.getEdad());
        cliente.setDireccion(clientePostReq.getDireccion());
        cliente.setTelefono(clientePostReq.getTelefono());
        cliente.setGenero(clientePostReq.getGenero());
        cliente.setContrasena(clientePostReq.getContrasena());
        cliente.setClienteId(clientePostReq.getClienteId());
        cliente.setNumeroDocumento(clientePostReq.getNumeroDocumento());
        cliente.setTipoDocumento(clientePostReq.getTipoDocumento());
        cliente.setActivo(clientePostReq.getActivo());
        return cliente;
    }

    public static Cliente build(ClientePutReq clientePutReq, Long id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(clientePutReq.getNombre());
        cliente.setEdad(clientePutReq.getEdad());
        cliente.setDireccion(clientePutReq.getDireccion());
        cliente.setTelefono(clientePutReq.getTelefono());
        cliente.setGenero(clientePutReq.getGenero());
        cliente.setContrasena(clientePutReq.getContrasena());
        cliente.setClienteId(clientePutReq.getClienteId());
        cliente.setNumeroDocumento(clientePutReq.getNumeroDocumento());
        cliente.setTipoDocumento(clientePutReq.getTipoDocumento());
        cliente.setActivo(clientePutReq.getActivo());
        return cliente;
    }

    public static void setearCamposNoNulos(Cliente oldCliente, Cliente cliente) {
        if(Objects.nonNull(cliente.getNombre())){
            oldCliente.setNombre(cliente.getNombre());
        }
        if(Objects.nonNull(cliente.getEdad())){
            oldCliente.setEdad(cliente.getEdad());
        }
        if(Objects.nonNull(cliente.getDireccion())){
            oldCliente.setDireccion(cliente.getDireccion());
        }
        if(Objects.nonNull(cliente.getTelefono())){
            oldCliente.setTelefono(cliente.getTelefono());
        }
        if(Objects.nonNull(cliente.getGenero())){
            oldCliente.setGenero(cliente.getGenero());
        }
        if(Objects.nonNull(cliente.getContrasena())){
            oldCliente.setContrasena(cliente.getContrasena());
        }
        if(Objects.nonNull(cliente.getClienteId())){
            oldCliente.setClienteId(cliente.getClienteId());
        }
        if(Objects.nonNull(cliente.getNumeroDocumento())){
            oldCliente.setNumeroDocumento(cliente.getNumeroDocumento());
        }
        if(Objects.nonNull(cliente.getTipoDocumento())){
            oldCliente.setTipoDocumento(cliente.getTipoDocumento());
        }
        if(Objects.nonNull(cliente.getActivo())){
            oldCliente.setActivo(cliente.getActivo());
        }
    }

    public static ClienteResponse build(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setGenero(cliente.getGenero());
        clienteResponse.setEdad(cliente.getEdad());
        clienteResponse.setDireccion(cliente.getDireccion());
        clienteResponse.setTelefono(cliente.getTelefono());
        clienteResponse.setClienteId(cliente.getClienteId());
        clienteResponse.setTipoDocumento(cliente.getTipoDocumento());
        clienteResponse.setNumeroDocumento(cliente.getNumeroDocumento());
        clienteResponse.setActivo(cliente.getActivo());
        return clienteResponse;
    }
}
