package com.aularestudemy.udemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		//SpringApplication.run(Startup.class, args);
		
		
		ApplicationContext context = SpringApplication.run(Startup.class, args);
		  // Obtém o PasswordEncoder do contexto
        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        // Senha a ser criptografada
        String rawPassword = "senha123";

        // Criptografa a senha
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        // Exibe a senha criptografada
        System.out.println("Senha criptografada: " + encryptedPassword);
		
		
	}
	
	

}
