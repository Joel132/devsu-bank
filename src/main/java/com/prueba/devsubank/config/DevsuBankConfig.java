package com.prueba.devsubank.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties("devsubank")
public class DevsuBankConfig {
    private BigDecimal limiteDiarioRetiro;

    private String secret;

    public BigDecimal getLimiteDiarioRetiro() {
        return limiteDiarioRetiro;
    }

    public void setLimiteDiarioRetiro(BigDecimal limiteDiarioRetiro) {
        this.limiteDiarioRetiro = limiteDiarioRetiro;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
