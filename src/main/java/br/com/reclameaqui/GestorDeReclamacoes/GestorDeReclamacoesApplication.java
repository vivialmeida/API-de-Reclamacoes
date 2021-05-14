package br.com.reclameaqui.GestorDeReclamacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GestorDeReclamacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorDeReclamacoesApplication.class, args);
	}

}
