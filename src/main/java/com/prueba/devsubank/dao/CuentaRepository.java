package com.prueba.devsubank.dao;

import com.prueba.devsubank.dao.model.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

    Optional<Cuenta> findCuentaByNumero(String numero);


}
