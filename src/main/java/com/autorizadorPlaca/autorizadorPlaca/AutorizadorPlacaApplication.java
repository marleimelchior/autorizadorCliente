package com.autorizadorPlaca.autorizadorPlaca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.autorizadorPlaca.autorizadorPlaca.repository")

public class AutorizadorPlacaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutorizadorPlacaApplication.class, args);
	}

}
