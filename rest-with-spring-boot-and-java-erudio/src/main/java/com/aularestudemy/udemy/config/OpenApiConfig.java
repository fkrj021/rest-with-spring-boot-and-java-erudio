package com.aularestudemy.udemy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Aula Udemy RESTful")
						.version("v1")
						.description("Teste")
						.termsOfService("")
						.license(new License()
								.name("Apache 2.0")
								.url("https://google.com")
								)
						);
	}
    
    

}
