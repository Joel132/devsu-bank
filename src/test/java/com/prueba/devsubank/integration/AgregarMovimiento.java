package com.prueba.devsubank.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.devsubank.dao.ClienteRepository;
import com.prueba.devsubank.dao.CuentaRepository;
import com.prueba.devsubank.dao.MovimientoRepository;
import com.prueba.devsubank.dto.*;
import com.prueba.devsubank.enums.Genero;
import com.prueba.devsubank.enums.TipoMovimiento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class AgregarMovimiento {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Test
    void registrationWorksThroughAllLayers() throws Exception {
        ClientePostReq clientePostReq = buildCliente();

        ClienteResponse clienteResponse = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clientePostReq)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(),ClienteResponse.class);

        Long count = clienteRepository.count();
        assertEquals(count, 1l);

        CuentaPostReq cuentaPostReq = buildCuenta();

        CuentaResponse cuentaResponse = objectMapper.readValue(mockMvc.perform(MockMvcRequestBuilders.post(String.format("/clientes/%d/cuentas",clienteResponse.getId()))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cuentaPostReq)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn().getResponse().getContentAsString(),CuentaResponse.class);

        count = cuentaRepository.count();
        assertEquals(count, 1l);

        MovimientoPostReq movimientoPostReq = buildMovimiento();

        mockMvc.perform(MockMvcRequestBuilders.post(String.format("/cuentas/%d/movimientos",cuentaResponse.getId()))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(movimientoPostReq)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        count = movimientoRepository.count();
        assertEquals(count, 1l);

    }

    private static MovimientoPostReq buildMovimiento() {
        MovimientoPostReq movimientoPostReq = new MovimientoPostReq();
        movimientoPostReq.setValor(BigDecimal.valueOf(10000));
        movimientoPostReq.setTipoMovimiento(TipoMovimiento.CREDITO);
        return movimientoPostReq;
    }

    private static CuentaPostReq buildCuenta() {
        CuentaPostReq cuentaPostReq = new CuentaPostReq();
        cuentaPostReq.setNumero( "213321");
        cuentaPostReq.setTipo( "CBA");
        cuentaPostReq.setSaldoInicial(BigDecimal.valueOf(100000));
        cuentaPostReq.setMoneda( "USD");
        cuentaPostReq.setActivo(true);
        return cuentaPostReq;
    }

    private static ClientePostReq buildCliente() {
        ClientePostReq clientePostReq = new ClientePostReq();
        clientePostReq.setNombre( "SAD");
        clientePostReq.setDireccion("Mi dierccion");
        clientePostReq.setTelefono( "019313283812");
        clientePostReq.setEdad( 19);
        clientePostReq.setGenero(Genero.M);
        clientePostReq.setContrasena( "12345678");
        clientePostReq.setClienteId( "{{$randomUUID}}");
        clientePostReq.setTipoDocumento( "CI");
        clientePostReq.setNumeroDocumento( "43243");
        clientePostReq.setActivo(true);
        return clientePostReq;
    }
}
