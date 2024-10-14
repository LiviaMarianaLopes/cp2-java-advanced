package br.com.fiap.cp2;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "API de diplomas", version = "0.0.1", description = "API de diplomas para a realização do CP2"))
public class Cp2JavaAdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(Cp2JavaAdvancedApplication.class, args);
	}

}
