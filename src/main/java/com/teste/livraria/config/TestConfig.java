package com.teste.livraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.teste.livraria.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	DBService dbService;
	
	@Bean
	public void instanciaBaseDados() {
		this.dbService.populaBDInicial();
	}
}
