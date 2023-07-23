package com.prueba.devsubank.service;

import com.prueba.devsubank.config.DevsuBankConfig;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.MovimientoRepository;
import com.prueba.devsubank.dao.model.Cuenta;
import com.prueba.devsubank.dao.model.Movimiento;
import com.prueba.devsubank.dto.MovimientoPostReq;
import com.prueba.devsubank.enums.TipoMovimiento;
import com.prueba.devsubank.exceptions.BankException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovimientoServiceTest {

    @Mock
    MovimientoRepository movimientoRepository;

    @Mock
    CuentaRepository cuentaRepository;
    @Mock

    DevsuBankConfig configProps;
    @InjectMocks
    MovimientoService movimientoService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void registrarMovimientoLimiteDiarioExcedido() {

        BigDecimal valorADebitar = BigDecimal.valueOf(1000);

        BigDecimal maximoDiario = BigDecimal.valueOf(5000);

        BigDecimal valorDebitadoHoy = BigDecimal.valueOf(-4500);//en negativo

        BigDecimal saldoActual = BigDecimal.valueOf(10000);

        Mockito.when(configProps.getLimiteDiarioRetiro())
                .thenReturn(maximoDiario);

        //para pasar
        Mockito.when(cuentaRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(new Cuenta()));

        //para saber cuanto ya debito el dia de hoy
        Mockito.when(movimientoRepository.findMovimientosByFechaBetweenAndTipoEquals(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(List.of(build(valorDebitadoHoy,BigDecimal.valueOf(1000))));

        //para ver saldo antes de movimiento
        Mockito.when(movimientoRepository.findFirstByCuentaOrderByIdDesc(Mockito.any()))
                .thenReturn(Optional.of(build(BigDecimal.valueOf(1000), saldoActual)));

        assertThrows(BankException.class,()->movimientoService.registrarMovimiento(build(valorADebitar),1l));
    }

    @Test
    void registrarMovimientoSaldoNoDisponible() {

        BigDecimal valorADebitar = BigDecimal.valueOf(1000);

        BigDecimal maximoDiario = BigDecimal.valueOf(5000);

        BigDecimal valorDebitadoHoy = BigDecimal.valueOf(-1500);//en negativo

        BigDecimal saldoActual = BigDecimal.valueOf(900);

        //para pasar
        Mockito.when(cuentaRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(new Cuenta()));

        //para saber cuanto ya debito el dia de hoy
        Mockito.when(movimientoRepository.findMovimientosByFechaBetweenAndTipoEquals(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(List.of(build(valorDebitadoHoy,BigDecimal.valueOf(1000))));

        //para ver saldo antes de movimiento
        Mockito.when(movimientoRepository.findFirstByCuentaOrderByIdDesc(Mockito.any()))
                .thenReturn(Optional.of(build(BigDecimal.valueOf(1000), saldoActual)));

        assertThrows(BankException.class,()->movimientoService.registrarMovimiento(build(valorADebitar),1l));
    }

    private Movimiento build(BigDecimal valor, BigDecimal saldo) {
        Movimiento movimiento = new Movimiento();
        movimiento.setSaldo(saldo);
        movimiento.setValor(valor);
        return movimiento;
    }

    private MovimientoPostReq build(BigDecimal valor) {
        MovimientoPostReq movimientoPostReq = new MovimientoPostReq();
        movimientoPostReq.setTipoMovimiento(TipoMovimiento.DEBITO);
        movimientoPostReq.setValor(valor);
        return movimientoPostReq;
    }
}