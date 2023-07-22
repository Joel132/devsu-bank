package com.prueba.devsubank.dao;

import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    Optional<Movimiento> findFirstByCuentaOrderByIdDesc(Cuenta cuenta);

    List<Movimiento> findMovimientosByCuentaAndIdIsGreaterThan(Cuenta cuenta, Long id);

    List<Movimiento> findMovimientosByFechaBetweenAndTipoEqualsIgnoreCase(OffsetDateTime today, OffsetDateTime tomorrow, String tipo);

    List<Movimiento> findMovimientosByCuenta_Cliente_IdAndFechaBetweenOrderByIdAsc(Long clienteId, OffsetDateTime to, OffsetDateTime t1);

}
