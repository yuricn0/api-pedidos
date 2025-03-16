package com.ydcns.pedidos.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ydcns.pedidos.entities.User;
import com.ydcns.pedidos.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {   // implementar commandLineRunner para executar quando a aplicação for executada.
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "95545588", "18765656");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
}
