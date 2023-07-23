package com.prueba.devsubank.dao;

import com.prueba.devsubank.dao.model.Cuenta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

    Optional<Cuenta> findCuentaByNumero(String numero);

    @Modifying
    @Transactional
    @Query("update Cuenta c set c.activo = ?1 where c.cliente.id = ?2")
    int updateEstadoForCuentasByCliente(Boolean estado, long clienteId);



}
