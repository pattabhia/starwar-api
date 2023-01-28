package com.devcode.starwar.api.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@Service
@Slf4j
public abstract class AbstractAPIService<R> implements APIService<R> {

    @Autowired private WebClient webClient;

    @Autowired private RestTemplate restTemplate;

    public abstract R findCount();

    public abstract String getType();

}
