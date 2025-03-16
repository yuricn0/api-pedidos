package com.ydcns.pedidos.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ydcns.pedidos.entities.Order;
import com.ydcns.pedidos.entities.User;
import com.ydcns.pedidos.repositories.OrderRepository;
import com.ydcns.pedidos.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {   // implementar commandLineRunner para executar quando a aplicação for executada.
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "95545588", "18765656");
		
		Order o1 = new Order(null, Instant.parse("2024-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2023-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2023-07-22T15:21:22Z"), u1);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));	
	}
	
}
