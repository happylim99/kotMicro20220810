package com.sean.webclient.config;

import com.sean.webclient.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    private final AuthServiceImpl authService;

    @Autowired
    public WebClientConfig(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @Bean("authWebClient")
    public WebClient authWebClient() {
        return WebClient.builder()
                .filter(ExchangeFilterFunction.ofRequestProcessor(
                        (ClientRequest request) -> Mono.just(
                                ClientRequest.from(request)
                                        .header("Authorization", "Bearer " + authService.getToken())
                                        .build()
                        )
                ))
                .build();
    }

}
