package com.prueba.devsubank.util;

import com.prueba.devsubank.dao.model.Cliente;
import com.prueba.devsubank.dto.ClientePostReq;

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
        return cliente;
    }
}
