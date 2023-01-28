package com.devcode.starwar.api.config;

import com.devcode.starwar.api.exception.ErrorMessage;
import com.devcode.starwar.api.exception.ResourceNotFoundException;
import com.devcode.starwar.api.exception.TechnicalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
@Slf4j
public class WebConfig {

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest ->
        {
            log.info("Request {} {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofRequestProcessor(response ->
        {
            log.info("Response {} {}", response.method(), response.url());
            return Mono.just(response);
        });
    }

    private static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(ErrorMessage.class)
                        .flatMap(errorBody -> Mono.error(new TechnicalException(errorBody.getMessage())));
            } else if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.bodyToMono(ErrorMessage.class)
                        .flatMap(errorBody -> Mono.error(new ResourceNotFoundException(errorBody.getMessage())));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
}