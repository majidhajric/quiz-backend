package com.example.caller.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class CallerController {

    private final WebClient.Builder loadBalancedWebClientBuilder;

    public CallerController(WebClient.Builder loadBalancedWebClientBuilder) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
    }

    @RequestMapping("/hi")
    public Mono<String> hi() {
        return loadBalancedWebClientBuilder.build()
                .get().uri("http://greetings-service/greetings")
                .retrieve()
                .bodyToMono(String.class);
    }
}
