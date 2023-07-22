package com.prueba.devsubank.dao;

import com.prueba.devsubank.dao.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findClienteByClienteId(String clienteId);

    Optional<Cliente> findClienteByNumeroDocumentoAndTipoDocumento(String numeroDocumento, String tipoDocumento);
}
