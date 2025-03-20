package com.ydcns.pedidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API E-commerce")
				.description("API RESTful desenvolvida para gerenciar pedidos de um e-commerce.")
				.contact(new Contact()
						.name("Yuri da Cruz Nunes")
						.email("yuri.cruz10@hotmail.com")
						.url("https://github.com/yuricn0"))
				.version("Versão 1.0"));
	}
}