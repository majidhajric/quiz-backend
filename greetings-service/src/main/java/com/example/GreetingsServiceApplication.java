package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class GreetingsServiceApplication {

	@Value("${server.port}")
	private Integer port;

	public static void main(String[] args) {
		SpringApplication.run(GreetingsServiceApplication.class, args);
	}

	@GetMapping(path = "/greetings")
	public Mono<String> greeting() {
		return Mono.just("Hello from port: " + port);
	}
}
