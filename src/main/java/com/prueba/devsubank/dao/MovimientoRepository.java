package com.prueba.devsubank.dao;

import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.enums.TipoMovimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    Optional<Movimiento> findFirstByCuentaOrderByIdDesc(Cuenta cuenta);

    List<Movimiento> findMovimientosByCuentaAndIdIsGreaterThan(Cuenta cuenta, Long id);

    List<Movimiento> findMovimientosByFechaBetweenAndTipoEquals(LocalDate today, LocalDate tomorrow, TipoMovimiento tipo);

    List<Movimiento> findMovimientosByCuenta_Cliente_IdAndFechaBetweenOrderByIdAsc(Long clienteId, LocalDate to, LocalDate t1);

}
