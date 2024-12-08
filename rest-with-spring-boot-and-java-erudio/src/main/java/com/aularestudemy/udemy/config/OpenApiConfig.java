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
						.title("Api RESTful")
						.version("v1")
						.description("CRUD via API")
						.termsOfService("")
						.license(new License()
								.name("Visual Mix")
								.url("https://visualmix.com.br")
								)
						);
	}
    
    

}
