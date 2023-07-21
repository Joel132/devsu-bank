package com.prueba.devsubank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DevsuBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevsuBankApplication.class, args);
	}

}
