package com.prueba.devsubank.dao;

import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    Optional<Movimiento> findFirstByCuentaOrderByIdDesc(Cuenta cuenta);

}
